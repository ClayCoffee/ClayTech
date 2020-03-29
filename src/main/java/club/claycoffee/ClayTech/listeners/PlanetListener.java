package club.claycoffee.ClayTech.listeners;

import org.bukkit.World;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Boss;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.event.entity.EntityDamageEvent;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.api.ClayTechManager;
import club.claycoffee.ClayTech.api.Planet;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.PlanetUtils;
import club.claycoffee.ClayTech.utils.RocketUtils;
import club.claycoffee.ClayTech.utils.Utils;

public class PlanetListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void EntitySpawnEvent(EntitySpawnEvent e) {
		Planet p = PlanetUtils.getPlanet(e.getEntity().getWorld());
		if (p != null) {
			if (!p.spawnMob) {
				if (e.getEntity() instanceof Mob || e.getEntity() instanceof Animals || e.getEntity() instanceof Monster
						|| e.getEntity() instanceof Boss) {
					e.setCancelled(true);
				}

			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerChangeWorldEvent(PlayerChangedWorldEvent e) {
		e.getPlayer().removePotionEffect(PotionEffectType.JUMP);

		Planet p = PlanetUtils.getPlanet(e.getPlayer().getWorld());
		if (p != null) {
			if (!p.habitable) {
				if (ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getHelmet())
						&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getChestplate())
						&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getLeggings())
						&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getBoots())) {
					World PreviousWorld = e.getPlayer().getWorld();
					new BukkitRunnable() {

						@Override
						public void run() {
							if (!PreviousWorld.equals(e.getPlayer().getWorld()) || !e.getPlayer().isOnline())
								this.cancel();
							// 扣氧气线程
							if (ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getHelmet())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getChestplate())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getLeggings())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getBoots())) {
								if (RocketUtils.getOxygen(e.getPlayer().getInventory().getHelmet()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getChestplate()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getLeggings()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getBoots()) > 0) {
									ItemStack helmet = e.getPlayer().getInventory().getHelmet();
									RocketUtils.setOxygen(helmet, RocketUtils.getOxygen(helmet) - 1);

									ItemStack chestplate = e.getPlayer().getInventory().getChestplate();
									RocketUtils.setOxygen(chestplate, RocketUtils.getOxygen(chestplate) - 1);

									ItemStack leggings = e.getPlayer().getInventory().getLeggings();
									RocketUtils.setOxygen(leggings, RocketUtils.getOxygen(leggings) - 1);

									ItemStack boots = e.getPlayer().getInventory().getBoots();
									RocketUtils.setOxygen(boots, RocketUtils.getOxygen(boots) - 1);
								}
							}
						}

					}.runTaskTimerAsynchronously(ClayTech.getInstance(), 1200, 1200);
					new BukkitRunnable() {

						@SuppressWarnings("deprecation")
						@Override
						public void run() {
							if (!PreviousWorld.equals(e.getPlayer().getWorld()) || !e.getPlayer().isOnline())
								this.cancel();

							if (!(ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getHelmet())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getChestplate())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getLeggings())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getBoots()))) {
								// 扣血
								e.getPlayer().sendTitle(Lang.readGeneralText("SpaceSuitError"),
										Lang.readGeneralText("SpaceSuitError_Sub"));
								e.getPlayer().damage(5);

							} else {
								if (!(RocketUtils.getOxygen(e.getPlayer().getInventory().getHelmet()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getChestplate()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getLeggings()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getBoots()) > 0)) {
									// 扣血
									e.getPlayer().sendTitle(Lang.readGeneralText("SpaceSuitError"),
											Lang.readGeneralText("SpaceSuitError_Sub"));
									e.getPlayer().damage(5);
								} else {
									int harmlevel = p.harmlevel;
									if (RocketUtils
											.getProtectLevel(e.getPlayer().getInventory().getHelmet()) < harmlevel
											|| RocketUtils.getProtectLevel(
													e.getPlayer().getInventory().getChestplate()) < harmlevel
											|| RocketUtils.getProtectLevel(
													e.getPlayer().getInventory().getLeggings()) < harmlevel
											|| RocketUtils.getProtectLevel(
													e.getPlayer().getInventory().getBoots()) < harmlevel) {
										// 扣血
										e.getPlayer().sendTitle(Lang.readGeneralText("SpaceSuitError"),
												Lang.readGeneralText("SpaceSuitError_Sub"));
										e.getPlayer().damage(5);
									}
								}
							}
						}

					}.runTaskTimer(ClayTech.getInstance(), 20, 20);
				} else {
					World PreviousWorld = e.getPlayer().getWorld();
					new BukkitRunnable() {

						@SuppressWarnings("deprecation")
						@Override
						public void run() {
							if (!PreviousWorld.equals(e.getPlayer().getWorld()) || !e.getPlayer().isOnline())
								this.cancel();

							if (!(ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getHelmet())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getChestplate())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getLeggings())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getBoots()))) {
								// 扣血
								e.getPlayer().sendTitle(Lang.readGeneralText("SpaceSuitError"),
										Lang.readGeneralText("SpaceSuitError_Sub"));
								e.getPlayer().damage(5);

							} else {
								if (!(RocketUtils.getOxygen(e.getPlayer().getInventory().getHelmet()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getChestplate()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getLeggings()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getBoots()) > 0)) {
									// 扣血
									e.getPlayer().sendTitle(Lang.readGeneralText("SpaceSuitError"),
											Lang.readGeneralText("SpaceSuitError_Sub"));
									e.getPlayer().damage(5);
								} else {
									int harmlevel = p.harmlevel;
									if (RocketUtils
											.getProtectLevel(e.getPlayer().getInventory().getHelmet()) < harmlevel
											|| RocketUtils.getProtectLevel(
													e.getPlayer().getInventory().getChestplate()) < harmlevel
											|| RocketUtils.getProtectLevel(
													e.getPlayer().getInventory().getLeggings()) < harmlevel
											|| RocketUtils.getProtectLevel(
													e.getPlayer().getInventory().getBoots()) < harmlevel) {
										// 扣血
										e.getPlayer().sendTitle(Lang.readGeneralText("SpaceSuitError"),
												Lang.readGeneralText("SpaceSuitError_Sub"));
										e.getPlayer().damage(5);
									}
								}
							}
						}

					}.runTaskTimer(ClayTech.getInstance(), 20, 20);
				}
			}
			if (p.gravity > 1) {
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, p.gravity - 1));
			}
		}
	}

	@EventHandler
	public void PlayerJoinEvent(PlayerJoinEvent e) {
		e.getPlayer().removePotionEffect(PotionEffectType.JUMP);

		Planet p = PlanetUtils.getPlanet(e.getPlayer().getWorld());
		if (p != null) {
			if (!p.habitable) {
				if (ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getHelmet())
						&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getChestplate())
						&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getLeggings())
						&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getBoots())) {
					World PreviousWorld = e.getPlayer().getWorld();
					new BukkitRunnable() {

						@Override
						public void run() {
							if (!PreviousWorld.equals(e.getPlayer().getWorld()) || !e.getPlayer().isOnline())
								this.cancel();
							// 扣氧气线程
							if (ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getHelmet())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getChestplate())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getLeggings())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getBoots())) {
								if (RocketUtils.getOxygen(e.getPlayer().getInventory().getHelmet()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getChestplate()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getLeggings()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getBoots()) > 0) {
									ItemStack helmet = e.getPlayer().getInventory().getHelmet();
									RocketUtils.setOxygen(helmet, RocketUtils.getOxygen(helmet) - 1);

									ItemStack chestplate = e.getPlayer().getInventory().getChestplate();
									RocketUtils.setOxygen(chestplate, RocketUtils.getOxygen(chestplate) - 1);

									ItemStack leggings = e.getPlayer().getInventory().getLeggings();
									RocketUtils.setOxygen(leggings, RocketUtils.getOxygen(leggings) - 1);

									ItemStack boots = e.getPlayer().getInventory().getBoots();
									RocketUtils.setOxygen(boots, RocketUtils.getOxygen(boots) - 1);
								}
							}
						}

					}.runTaskTimerAsynchronously(ClayTech.getInstance(), 1200, 1200);
					new BukkitRunnable() {

						@SuppressWarnings("deprecation")
						@Override
						public void run() {
							if (!PreviousWorld.equals(e.getPlayer().getWorld()) || !e.getPlayer().isOnline())
								this.cancel();

							if (!(ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getHelmet())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getChestplate())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getLeggings())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getBoots()))) {
								// 扣血
								e.getPlayer().sendTitle(Lang.readGeneralText("SpaceSuitError"),
										Lang.readGeneralText("SpaceSuitError_Sub"));
								e.getPlayer().damage(5);

							} else {
								if (!(RocketUtils.getOxygen(e.getPlayer().getInventory().getHelmet()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getChestplate()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getLeggings()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getBoots()) > 0)) {
									// 扣血
									e.getPlayer().sendTitle(Lang.readGeneralText("SpaceSuitError"),
											Lang.readGeneralText("SpaceSuitError_Sub"));
									e.getPlayer().damage(5);
								} else {
									int harmlevel = p.harmlevel;
									if (RocketUtils
											.getProtectLevel(e.getPlayer().getInventory().getHelmet()) < harmlevel
											|| RocketUtils.getProtectLevel(
													e.getPlayer().getInventory().getChestplate()) < harmlevel
											|| RocketUtils.getProtectLevel(
													e.getPlayer().getInventory().getLeggings()) < harmlevel
											|| RocketUtils.getProtectLevel(
													e.getPlayer().getInventory().getBoots()) < harmlevel) {
										// 扣血
										e.getPlayer().sendTitle(Lang.readGeneralText("SpaceSuitError"),
												Lang.readGeneralText("SpaceSuitError_Sub"));
										e.getPlayer().damage(5);
									}
								}
							}
						}

					}.runTaskTimer(ClayTech.getInstance(), 20, 20);
				} else {
					World PreviousWorld = e.getPlayer().getWorld();
					new BukkitRunnable() {

						@SuppressWarnings("deprecation")
						@Override
						public void run() {
							if (!PreviousWorld.equals(e.getPlayer().getWorld()) || !e.getPlayer().isOnline())
								this.cancel();

							if (!(ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getHelmet())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getChestplate())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getLeggings())
									&& ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getBoots()))) {
								// 扣血
								e.getPlayer().sendTitle(Lang.readGeneralText("SpaceSuitError"),
										Lang.readGeneralText("SpaceSuitError_Sub"));
								e.getPlayer().damage(5);

							} else {
								if (!(RocketUtils.getOxygen(e.getPlayer().getInventory().getHelmet()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getChestplate()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getLeggings()) > 0
										&& RocketUtils.getOxygen(e.getPlayer().getInventory().getBoots()) > 0)) {
									// 扣血
									e.getPlayer().sendTitle(Lang.readGeneralText("SpaceSuitError"),
											Lang.readGeneralText("SpaceSuitError_Sub"));
									e.getPlayer().damage(5);
								} else {
									int harmlevel = p.harmlevel;
									if (RocketUtils
											.getProtectLevel(e.getPlayer().getInventory().getHelmet()) < harmlevel
											|| RocketUtils.getProtectLevel(
													e.getPlayer().getInventory().getChestplate()) < harmlevel
											|| RocketUtils.getProtectLevel(
													e.getPlayer().getInventory().getLeggings()) < harmlevel
											|| RocketUtils.getProtectLevel(
													e.getPlayer().getInventory().getBoots()) < harmlevel) {
										// 扣血
										e.getPlayer().sendTitle(Lang.readGeneralText("SpaceSuitError"),
												Lang.readGeneralText("SpaceSuitError_Sub"));
										e.getPlayer().damage(5);
									}
								}
							}
						}

					}.runTaskTimer(ClayTech.getInstance(), 20, 20);
				}
			}
			if (p.gravity > 1) {
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, p.gravity - 1));
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerTeleportEvent(PlayerTeleportEvent e) {
		Planet p = PlanetUtils.getPlanet(e.getPlayer().getWorld());
		Planet to = PlanetUtils.getPlanet(e.getTo().getWorld());
		String inRocket = "false";
		if (p != null) {
			if (to != null) {
				if (p.planetName.equalsIgnoreCase(to.planetName)) {
					// 如果目标位置在当前世界
					return;
				}
			}
			if (Utils.readPlayerMetadataString(e.getPlayer(), "inrocket") != null) {
				inRocket = Utils.readPlayerMetadataString(e.getPlayer(), "inrocket");
			}
			boolean ast = Utils.readPlayerMetadataBoolean(e.getPlayer(), "allowSpaceTeleport");
			if (!inRocket.equalsIgnoreCase("true") && !p.planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
				if(ast) {
					e.getPlayer().setMetadata("allowSpaceTeleport", new FixedMetadataValue(ClayTech.getInstance(),false));
					return;
				}
				// 其他星球传送到主世界
				e.getPlayer().sendMessage(Lang.readGeneralText("CantUseOtherTeleportInUniverse"));
				e.setCancelled(true);
				return;
			}
		}

		if (to != null && p != null) {
			if (Utils.readPlayerMetadataString(e.getPlayer(), "inrocket") != null) {
				inRocket = Utils.readPlayerMetadataString(e.getPlayer(), "inrocket");
			}
			boolean ast = Utils.readPlayerMetadataBoolean(e.getPlayer(), "allowSpaceTeleport");
			if (!inRocket.equalsIgnoreCase("true") && p.planetName.equalsIgnoreCase(ClayTech.getOverworld())
					&& !to.planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
				if(ast) {
					e.getPlayer().setMetadata("allowSpaceTeleport", new FixedMetadataValue(ClayTech.getInstance(),false));
					return;
				}
				// 在主世界传送到其他星球
				e.getPlayer().sendMessage(Lang.readGeneralText("CantUseOtherTeleportInUniverse"));
				e.setCancelled(true);
				return;
			}
		}
	}
	
	@EventHandler
	public void EntityDamageEvent(EntityDamageEvent e) {
		if(e.getEntityType() == EntityType.PLAYER && e.getCause() == DamageCause.FALL) {
			Player p = (Player) e.getEntity();
			if (ClayTechManager.isSpaceSuit(p.getInventory().getHelmet())
					&& ClayTechManager.isSpaceSuit(p.getInventory().getChestplate())
					&& ClayTechManager.isSpaceSuit(p.getInventory().getLeggings())
					&& ClayTechManager.isSpaceSuit(p.getInventory().getBoots())) {
				e.setDamage(e.getDamage() - e.getFinalDamage());
				if(Utils.readPlayerMetadataBoolean(p, "SpaceSuitNoCostDurability")) {
					e.setCancelled(true);
					p.setMetadata("SpaceSuitNoCostDurability", new FixedMetadataValue(ClayTech.getInstance(),false));
				}
				p.sendMessage(Lang.readGeneralText("SpaceSuitFall"));
			}
		}
	}
}
