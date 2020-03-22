package club.claycoffee.ClayTech.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.PlayerInventory;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.api.ClayTechManager;
import club.claycoffee.ClayTech.utils.DataYML;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.RocketUtils;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public class PlanetBaseListener implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void BlockPlaceEvent(BlockPlaceEvent e) {
		if (SlimefunManager.isItemSimilar(e.getPlayer().getInventory().getItemInMainHand(),ClayTechItems.PLANET_BASE_SIGNER,true)) {
			DataYML planetsData = ClayTech.getPlanetDataYML();
			FileConfiguration pd = planetsData.getCustomConfig();
			if (!pd.getBoolean(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".base")) {
				pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".base", true);
				pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseX",
						e.getBlock().getX());
				pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseY",
						e.getBlock().getY() + 1);
				pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseZ",
						e.getBlock().getZ());
				planetsData.saveCustomConfig();
				e.getPlayer().sendMessage(Lang.readGeneralText("BaseCompleted"));
			} else {
				e.getPlayer().sendMessage(Lang.readGeneralText("BaseExists"));
				planetsData.saveCustomConfig();
				e.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void BlockBreakEvent(BlockBreakEvent e) {
		if(BlockStorage.checkID(e.getBlock()) != null) {
			if (BlockStorage.checkID(e.getBlock()).equalsIgnoreCase("PLANET_BASE_SIGNER")) {
				DataYML planetsData = ClayTech.getPlanetDataYML();
				FileConfiguration pd = planetsData.getCustomConfig();
				pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".base", false);
				pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseX", null);
				pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseY", null);
				pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseZ", null);
				planetsData.saveCustomConfig();
				e.getPlayer().sendMessage(Lang.readGeneralText("BaseDestroyed"));
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerInteractEvent(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR) {
			if (e.hasItem()) {
				if (ClayTechManager.isOxygenDistributer(e.getItem())) {
					PlayerInventory piv = e.getPlayer().getInventory();
					if (ClayTechManager.isSpaceSuit(piv.getHelmet())
							&& ClayTechManager.isSpaceSuit(piv.getChestplate())
							&& ClayTechManager.isSpaceSuit(piv.getLeggings())
							&& ClayTechManager.isSpaceSuit(piv.getBoots())) {
						if(RocketUtils.getOxygen(e.getItem()) >= 4) {
							List<String> okPart = new ArrayList<String>();
							if(RocketUtils.getMaxOxygen(piv.getHelmet()) > RocketUtils.getOxygen(piv.getHelmet())) {
								okPart.add("HELMET");
							}
							if(RocketUtils.getMaxOxygen(piv.getChestplate()) > RocketUtils.getOxygen(piv.getChestplate())) {
								okPart.add("CHESTPLATE");
							}
							if(RocketUtils.getMaxOxygen(piv.getLeggings()) > RocketUtils.getOxygen(piv.getLeggings())) {
								okPart.add("LEGGINGS");
							}
							if(RocketUtils.getMaxOxygen(piv.getBoots()) > RocketUtils.getOxygen(piv.getBoots())) {
								okPart.add("BOOTS");
							}
							int totalOxygen = RocketUtils.getOxygen(e.getItem());
							int takeOxygen = totalOxygen / okPart.size();
							for(String eachPart : okPart) {
								totalOxygen -= takeOxygen;
								if(eachPart.equalsIgnoreCase("HELMET")) {
									RocketUtils.setOxygen(piv.getHelmet(),RocketUtils.getOxygen(piv.getHelmet())+takeOxygen);
								}
								if(eachPart.equalsIgnoreCase("CHESTPLATE")) {
									RocketUtils.setOxygen(piv.getChestplate(),RocketUtils.getOxygen(piv.getChestplate())+takeOxygen);
								}
								if(eachPart.equalsIgnoreCase("LEGGINGS")) {
									RocketUtils.setOxygen(piv.getLeggings(),RocketUtils.getOxygen(piv.getLeggings())+takeOxygen);
								}
								if(eachPart.equalsIgnoreCase("BOOTS")) {
									RocketUtils.setOxygen(piv.getBoots(),RocketUtils.getOxygen(piv.getBoots())+takeOxygen);
								}
							}
							RocketUtils.setOxygen(e.getItem(),totalOxygen);
							e.getPlayer().sendMessage(Lang.readGeneralText("SPACESUIT_DISTRIBUTER_OK"));

						}
						else {
							e.getPlayer().sendMessage(Lang.readGeneralText("SPACESUIT_4"));
						}
					}
					else {
						e.getPlayer().sendMessage(Lang.readGeneralText("SPACESUIT_NOT_OK"));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void InventoryClickEvent(InventoryClickEvent e) {
		if (e.getView().getTitle().equalsIgnoreCase(Lang.readMachinesText("CLAY_ROCKET_FUEL_INJECTOR"))
				&& e.getSlot() == 20) {
			if (ClayTech.RunningInjectors.get(e.getInventory()) != null) {
				e.setCancelled(true);
			}
		}
		if (e.getView().getTitle().equalsIgnoreCase(Lang.readMachinesText("CLAY_SPACESUIT_OXYGEN_INJECTOR"))
				&& e.getSlot() == 22) {
			if (ClayTech.RunningInjectorsOxygen.get(e.getInventory()) != null) {
				e.setCancelled(true);
			}
		}
	}
}
