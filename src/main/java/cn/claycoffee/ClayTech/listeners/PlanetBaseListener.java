package cn.claycoffee.ClayTech.listeners;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.ClayTechData;
import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.api.ClayTechManager;
import cn.claycoffee.ClayTech.api.Planet;
import cn.claycoffee.ClayTech.utils.DataYML;
import cn.claycoffee.ClayTech.utils.Lang;
import cn.claycoffee.ClayTech.utils.PlanetUtils;
import cn.claycoffee.ClayTech.utils.RocketUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

public class PlanetBaseListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockPlaceEvent(BlockPlaceEvent e) {
        if (SlimefunUtils.isItemSimilar(e.getPlayer().getInventory().getItemInMainHand(),
                ClayTechItems.PLANET_BASE_SIGNER, true)) {
            Planet p = PlanetUtils.getPlanet(e.getBlock().getWorld());
            if (p != null) {
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
                    return;
                } else {
                    e.getPlayer().sendMessage(Lang.readGeneralText("BaseExists"));
                    e.setCancelled(true);
                    return;
                }
            } else {
                e.getPlayer().sendMessage(Lang.readGeneralText("NotInPlanet"));
                e.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockBreakEvent(BlockBreakEvent e) {
        if (BlockStorage.checkID(e.getBlock()) != null) {
            if (BlockStorage.checkID(e.getBlock()).equalsIgnoreCase("PLANET_BASE_SIGNER")) {
                DataYML planetsData = ClayTech.getPlanetDataYML();
                FileConfiguration pd = planetsData.getCustomConfig();
                int baseX = pd.getInt(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseX");
                int baseY = pd.getInt(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseY");
                int baseZ = pd.getInt(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseZ");
                boolean hasBase = pd
                        .getBoolean(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".base");
                if (hasBase) {
                    if (baseX == e.getBlock().getX() && baseY == e.getBlock().getY() + 1
                            && baseZ == e.getBlock().getZ()) {
                        pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".base", false);
                        pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseX", null);
                        pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseY", null);
                        pd.set(e.getPlayer().getName() + "." + e.getPlayer().getWorld().getName() + ".baseZ", null);
                        planetsData.saveCustomConfig();
                        e.getPlayer().sendMessage(Lang.readGeneralText("BaseDestroyed"));
                    } else {
                        e.getPlayer().sendMessage(Lang.readGeneralText("NotYourBase"));
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerInteractEvent(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.hasItem()) {
                if (ClayTechManager.isOxygenDistributer(e.getItem())) {
                    PlayerInventory piv = e.getPlayer().getInventory();
                    if (ClayTechManager.isSpaceSuit(piv.getHelmet()) && ClayTechManager.isSpaceSuit(piv.getChestplate())
                            && ClayTechManager.isSpaceSuit(piv.getLeggings())
                            && ClayTechManager.isSpaceSuit(piv.getBoots())) {
                        if (RocketUtils.getOxygen(e.getItem()) >= 4) {
                            List<String> okPart = new ArrayList<String>();
                            if (RocketUtils.getMaxOxygen(piv.getHelmet()) > RocketUtils.getOxygen(piv.getHelmet())) {
                                okPart.add("HELMET");
                            }
                            if (RocketUtils.getMaxOxygen(piv.getChestplate()) > RocketUtils
                                    .getOxygen(piv.getChestplate())) {
                                okPart.add("CHESTPLATE");
                            }
                            if (RocketUtils.getMaxOxygen(piv.getLeggings()) > RocketUtils
                                    .getOxygen(piv.getLeggings())) {
                                okPart.add("LEGGINGS");
                            }
                            if (RocketUtils.getMaxOxygen(piv.getBoots()) > RocketUtils.getOxygen(piv.getBoots())) {
                                okPart.add("BOOTS");
                            }
                            int totalOxygen = RocketUtils.getOxygen(e.getItem());
                            if (okPart.size() == 0) {
                                e.getPlayer().sendMessage(Lang.readGeneralText("SPACESUIT_DISTRIBUTER_FULL"));
                                return;
                            }
                            int takeOxygen = totalOxygen / okPart.size();
                            for (String eachPart : okPart) {
                                totalOxygen -= takeOxygen;
                                int curOxg = 0;
                                if (eachPart.equalsIgnoreCase("HELMET")) {
                                    curOxg = RocketUtils.getOxygen(piv.getHelmet());
                                    if (curOxg + takeOxygen > RocketUtils.getMaxOxygen(piv.getHelmet())) {
                                        totalOxygen += curOxg + takeOxygen - RocketUtils.getMaxOxygen(piv.getHelmet());
                                        RocketUtils.setOxygen(piv.getHelmet(),
                                                RocketUtils.getMaxOxygen(piv.getHelmet()));

                                    } else {
                                        RocketUtils.setOxygen(piv.getHelmet(),
                                                RocketUtils.getOxygen(piv.getHelmet()) + takeOxygen);
                                    }
                                }
                                if (eachPart.equalsIgnoreCase("CHESTPLATE")) {
                                    curOxg = RocketUtils.getOxygen(piv.getChestplate());
                                    if (curOxg + takeOxygen > RocketUtils.getMaxOxygen(piv.getChestplate())) {
                                        totalOxygen += curOxg + takeOxygen
                                                - RocketUtils.getMaxOxygen(piv.getChestplate());
                                        RocketUtils.setOxygen(piv.getChestplate(),
                                                RocketUtils.getMaxOxygen(piv.getChestplate()));

                                    } else {
                                        RocketUtils.setOxygen(piv.getChestplate(),
                                                RocketUtils.getOxygen(piv.getChestplate()) + takeOxygen);
                                    }
                                }
                                if (eachPart.equalsIgnoreCase("LEGGINGS")) {
                                    curOxg = RocketUtils.getOxygen(piv.getLeggings());
                                    if (curOxg + takeOxygen > RocketUtils.getMaxOxygen(piv.getLeggings())) {
                                        totalOxygen += curOxg + takeOxygen
                                                - RocketUtils.getMaxOxygen(piv.getLeggings());
                                        RocketUtils.setOxygen(piv.getLeggings(),
                                                RocketUtils.getMaxOxygen(piv.getLeggings()));

                                    } else {
                                        RocketUtils.setOxygen(piv.getLeggings(),
                                                RocketUtils.getOxygen(piv.getLeggings()) + takeOxygen);
                                    }
                                }
                                if (eachPart.equalsIgnoreCase("BOOTS")) {
                                    curOxg = RocketUtils.getOxygen(piv.getBoots());
                                    if (curOxg + takeOxygen > RocketUtils.getMaxOxygen(piv.getBoots())) {
                                        totalOxygen += curOxg + takeOxygen - RocketUtils.getMaxOxygen(piv.getBoots());
                                        RocketUtils.setOxygen(piv.getBoots(), RocketUtils.getMaxOxygen(piv.getBoots()));

                                    } else {
                                        RocketUtils.setOxygen(piv.getBoots(),
                                                RocketUtils.getOxygen(piv.getBoots()) + takeOxygen);
                                    }
                                }
                            }
                            RocketUtils.setOxygen(e.getItem(), totalOxygen);
                            e.getPlayer().sendMessage(Lang.readGeneralText("SPACESUIT_DISTRIBUTER_OK"));

                        } else {
                            e.getPlayer().sendMessage(Lang.readGeneralText("SPACESUIT_4"));
                        }
                    } else {
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
            if (ClayTechData.RunningInjectors.get(e.getInventory()) != null) {
                e.setCancelled(true);
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase(Lang.readMachinesText("CLAY_SPACESUIT_OXYGEN_INJECTOR"))
                && e.getSlot() == 22) {
            if (ClayTechData.RunningInjectorsOxygen.get(e.getInventory()) != null) {
                e.setCancelled(true);
            }
        }
    }
}
