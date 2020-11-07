package cn.claycoffee.ClayTech.listeners;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.ClayTechData;
import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.api.ClayTechManager;
import cn.claycoffee.ClayTech.api.Planet;
import cn.claycoffee.ClayTech.api.events.RocketLandEvent;
import cn.claycoffee.ClayTech.utils.*;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RocketLauncherListener implements Listener {
    private static final int[] planet = {19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41,
            42, 43};

    @EventHandler
    public void InventoryMoveItemEvent(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
            if (e.getView().getTitle().equalsIgnoreCase(Lang.readMachinesText("ROCKET_LAUNCHER"))) {
                e.setCancelled(true);
                if (Utils.ExitsInList(e.getRawSlot(), planet)) {
                    if (e.getInventory().getItem(e.getRawSlot()) != null) {
                        ItemStack handItem = p.getInventory().getItemInMainHand();
                        if (ClayTechManager.isRocket(handItem)) {
                            // 是火箭
                            Inventory inv = e.getInventory();
                            Block b = ClayTechData.RunningLaunchersG.get(inv);
                            int currentPage = 1;
                            if (Utils.getMetadata(b, "currentPage") != null) {
                                currentPage = new Integer(Utils.getMetadata(b, "currentPage")).intValue();
                            }
                            int index = (currentPage - 1) * 21 + (e.getRawSlot() - 18) - 1;
                            Planet current = PlanetUtils.getPlanet(b.getWorld());
                            // 排列星球
                            List<Planet> pl = new ArrayList<Planet>();
                            for (Planet p1 : ClayTech.getPlanets()) {
                                pl.add(p1);
                            }
                            Planet[] pl2 = pl.toArray(new Planet[pl.size()]);
                            List<Integer> d = new ArrayList<Integer>();
                            for (Planet p1 : pl2) {
                                d.add((Integer) PlanetUtils.getDistance(current, p1));
                            }
                            Integer[] distance = d.toArray(new Integer[d.size()]);
                            for (int i = 0; i < distance.length; i++) {
                                for (int j = 0; j < distance.length - i - 1; j++) {
                                    if (distance[j].intValue() > distance[j + 1].intValue()) {
                                        int temp = distance[j + 1];
                                        distance[j + 1] = distance[j];
                                        distance[j] = temp;

                                        Planet temp2 = pl2[j + 1];
                                        pl2[j + 1] = pl2[j];
                                        pl2[j] = temp2;
                                    }
                                }
                            }
                            pl = Arrays.asList(pl2);

                            Planet target = pl.get(index);
                            if (!target.getPlanetWorldName().equalsIgnoreCase(current.getPlanetWorldName())) {
                                if (PlanetUtils.getFuel(current, target) <= RocketUtils.getFuel(handItem)) {
                                    if (handItem.getAmount() == 1) {
                                        if (ClayTech.isSpaceTravelNeedPerm()) {
                                            if (!p.hasPermission("claytech.travel." + target.getPlanetWorldName())) {
                                                p.sendMessage(Lang.readGeneralText("no_permission"));
                                                return;
                                            }
                                        }
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
                                                            BlockStorage.clearBlockInfo(b.getLocation(), true);
                                                            b.setType(Material.AIR);
                                                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sf give "+p.getName()+" ROCKET_LAUNCHER");
                                                            DataYML planetsData = ClayTech.getPlanetDataYML();
                                                            FileConfiguration pd = planetsData.getCustomConfig();
                                                            if (pd.getBoolean(p.getName() + "."
                                                                    + target.getPlanetWorldName() + ".base")) {
                                                                int X = pd.getInt(p.getName() + "."
                                                                        + target.getPlanetWorldName() + ".baseX");
                                                                int Y = pd.getInt(p.getName() + "."
                                                                        + target.getPlanetWorldName() + ".baseY");
                                                                int Z = pd.getInt(p.getName() + "."
                                                                        + target.getPlanetWorldName() + ".baseZ");

                                                                p.teleport(new Location(
                                                                        Bukkit.getWorld(target.getPlanetWorldName()), X,
                                                                        Y, Z), TeleportCause.PLUGIN);
                                                                p.sendTitle(Lang.readGeneralText("TeleportedToBase"),
                                                                        Lang.readGeneralText("TeleportedToBase_Sub"));
                                                            } else {
                                                                try {
                                                                    p.teleport(
                                                                            PlanetUtils.findSafeLocation(
                                                                                    Bukkit.getWorld(target
                                                                                            .getPlanetWorldName())),
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
                if (e.getRawSlot() == 46) {
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
                if (e.getRawSlot() == 52) {
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
