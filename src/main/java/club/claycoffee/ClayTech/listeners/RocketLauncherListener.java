package club.claycoffee.ClayTech.listeners;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechData;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.api.ClayTechManager;
import club.claycoffee.ClayTech.api.Planet;
import club.claycoffee.ClayTech.api.listeners.RocketLandEvent;
import club.claycoffee.ClayTech.utils.DataYML;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.PlanetUtils;
import club.claycoffee.ClayTech.utils.RocketUtils;
import club.claycoffee.ClayTech.utils.StrUtils;
import club.claycoffee.ClayTech.utils.Utils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.Slimefun;

import org.bukkit.event.inventory.InventoryClickEvent;

public class RocketLauncherListener implements Listener {
	private static final int[] BORDER = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 17, 18, 26, 27, 35, 36, 44, 45, 47, 48, 49,
			50, 51, 53 };
	private static final int[] BORDER_2 = { 10, 11, 12, 14, 15, 16 };
	private static final int[] planet = { 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41,
			42, 43 };

	@EventHandler
	public void InventoryMoveItemEvent(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			if (e.getView().getTitle().equalsIgnoreCase(Lang.readMachinesText("ROCKET_LAUNCHER"))) {
				if (Utils.ExitsInList(e.getSlot(), BORDER) || Utils.ExitsInList(e.getSlot(), BORDER_2)) {
					e.setCancelled(true);
				}
				if (Utils.ExitsInList(e.getSlot(), planet)) {
					if (e.getInventory().getItem(e.getSlot()) != null) {
						ItemStack handItem = p.getInventory().getItemInMainHand();
						if (ClayTechManager.isRocket(handItem)) {
							// 是火箭
							Inventory inv = e.getInventory();
							Block b = ClayTechData.RunningLaunchersG.get(inv);
							int currentPage = 1;
							if (Utils.getMetadata(b, "currentPage") != null) {
								currentPage = new Integer(Utils.getMetadata(b, "currentPage")).intValue();
							}
							int index = (currentPage - 1) * 21 + (e.getSlot() - 18) - 1;
							Planet target = ClayTech.getPlanets().get(index);
							Planet current = PlanetUtils.getPlanet(b.getWorld());
							if (!target.planetName.equalsIgnoreCase(current.planetName)) {
								if (PlanetUtils.getFuel(current, target) <= RocketUtils.getFuel(handItem)) {
									if (handItem.getAmount() == 1) {
										e.setCancelled(true);
										String inRocket = "false";
										if (Utils.readPlayerMetadataString(p, "inrocket") != null) {
											inRocket = Utils.readPlayerMetadataString(p, "inrocket");
										}
										if (!inRocket.equalsIgnoreCase("true")) {
											p.setMetadata("inrocket",
													new FixedMetadataValue(ClayTech.getInstance(), "true"));
											p.sendMessage(Lang.readGeneralText("RocketOK"));
											new BukkitRunnable() {
												int time = 0;

												@SuppressWarnings("deprecation")
												@Override
												public void run() {
													time++;
													p.sendMessage(Lang.readGeneralText("RocketCountdown")
															.replaceAll("%seconds%", "" + (10 - time)));
													ItemStack thandItem = p.getInventory().getItemInMainHand();
													if (ClayTechManager.isRocket(thandItem)) {
														if (time >= 10) {
															BlockStorage._integrated_removeBlockInfo(b.getLocation(),
																	true);
															b.setType(Material.AIR);
															p.getInventory().addItem(ClayTechItems.ROCKET_LAUNCHER);
															DataYML planetsData = ClayTech.getPlanetDataYML();
															FileConfiguration pd = planetsData.getCustomConfig();
															if (pd.getBoolean(
																	p.getName() + "." + target.planetName + ".base")) {
																int X = pd.getInt(p.getName() + "." + target.planetName
																		+ ".baseX");
																int Y = pd.getInt(p.getName() + "." + target.planetName
																		+ ".baseY");
																int Z = pd.getInt(p.getName() + "." + target.planetName
																		+ ".baseZ");
																p.teleport(
																		new Location(Bukkit.getWorld(target.planetName),
																				X, Y, Z),
																		TeleportCause.PLUGIN);
																p.sendTitle(Lang.readGeneralText("TeleportedToBase"),
																		Lang.readGeneralText("TeleportedToBase_Sub"));
															} else {
																try {
																	p.teleport(
																			PlanetUtils.findSafeLocation(
																					Bukkit.getWorld(target.planetName)),
																			TeleportCause.PLUGIN);
																} catch (Exception ex) {
																	p.sendMessage(
																			Lang.readGeneralText("LocationFatal"));
																	e.setCancelled(true);
																	return;
																}
															}
															p.setMetadata("inrocket", new FixedMetadataValue(
																	ClayTech.getInstance(), "false"));
															RocketUtils.setFuel(thandItem,
																	RocketUtils.getFuel(thandItem)
																			- PlanetUtils.getFuel(current, target));
															new BukkitRunnable() {

																@Override
																public void run() {
																	Bukkit.getPluginManager()
																			.callEvent(new RocketLandEvent(p, current,
																					target, thandItem));

																}

															}.runTask(ClayTech.getInstance());
															p.sendMessage(Lang.readGeneralText("RocketArrived"));
															this.cancel();
														}
													} else {
														p.setMetadata("inrocket", new FixedMetadataValue(
																ClayTech.getInstance(), "false"));
														p.sendMessage(Lang.readGeneralText("NotRocket"));
														this.cancel();
													}
												}

											}.runTaskTimer(ClayTech.getInstance(), 0, 20);
										} else {
											p.sendMessage(Lang.readGeneralText("AlreadyInRocket"));
											e.setCancelled(true);
										}
									} else {
										p.sendMessage(Lang.readGeneralText("StakingRockets"));
										e.setCancelled(true);
									}
								} else {
									p.sendMessage(Lang.readGeneralText("NotEnoughFuel"));
									e.setCancelled(true);
								}
							} else {
								p.sendMessage(Lang.readGeneralText("SamePlanet"));
								e.setCancelled(true);
							}
						} else {
							p.sendMessage(Lang.readGeneralText("NotRocket"));
							e.setCancelled(true);
						}
					} else {
						e.setCancelled(true);
						p.openInventory(e.getInventory());
					}
				}
				Inventory inv = e.getInventory();
				Block b = ClayTechData.RunningLaunchersG.get(inv);
				Planet current = PlanetUtils.getPlanet(b.getWorld());
				int currentPage = 1;
				if (e.getSlot() == 46) {
					// 上一页
					if (b != null) {
						if (Utils.getMetadata(b, "currentPage") != null) {
							currentPage = new Integer(Utils.getMetadata(b, "currentPage")).intValue();
						}
						if (currentPage > 1) {
							currentPage -= 1;
							Utils.setMetadata(b, "currentPage", currentPage + "");
							inv = PlanetUtils.renderLauncherMenu(current, inv, currentPage);
						} else {
							e.setCancelled(true);
							p.openInventory(inv);
						}
					}
				}
				if (e.getSlot() == 52) {
					// 下一页
					if (b != null) {
						if (Utils.getMetadata(b, "currentPage") != null) {
							currentPage = new Integer(Utils.getMetadata(b, "currentPage")).intValue();
						}
						if (currentPage < PlanetUtils.getTotalPage()) {
							currentPage += 1;
							Utils.setMetadata(b, "currentPage", currentPage + "");
							inv = PlanetUtils.renderLauncherMenu(current, inv, currentPage);
						} else {
							e.setCancelled(true);
							p.openInventory(inv);
						}
					}
				}
			}
		}
	}
}
