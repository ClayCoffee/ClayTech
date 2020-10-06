package cn.claycoffee.ClayTech.listeners;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.api.ClayTechManager;
import cn.claycoffee.ClayTech.api.Planet;
import cn.claycoffee.ClayTech.utils.Lang;
import cn.claycoffee.ClayTech.utils.PlanetUtils;
import cn.claycoffee.ClayTech.utils.RocketUtils;
import cn.claycoffee.ClayTech.utils.Utils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent.Cause;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PlanetListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void EntitySpawnEvent(EntitySpawnEvent e) {
        Planet p = PlanetUtils.getPlanet(e.getEntity().getWorld());
        if (p != null) {
            if (!p.getMobSpawnable()) {
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
        e.getPlayer().removePotionEffect(PotionEffectType.SLOW_FALLING);

        Planet p = PlanetUtils.getPlanet(e.getPlayer().getWorld());
        if (p != null) {
            if (!p.getHabitable()) {
                if (ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getHelmet())
                        && ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getChestplate())
                        && ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getLeggings())
                        && ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getBoots())) {
                    World PreviousWorld = e.getPlayer().getWorld();
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            if (!PreviousWorld.equals(e.getPlayer().getWorld()) || !e.getPlayer().isOnline()) {
                                this.cancel();
                                return;
                            }
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
                            if (!PreviousWorld.equals(e.getPlayer().getWorld()) || !e.getPlayer().isOnline()) {
                                this.cancel();
                                return;
                            }
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
                                    int harmlevel = p.getHarmLevel();
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
                            if (!PreviousWorld.equals(e.getPlayer().getWorld()) || !e.getPlayer().isOnline()) {
                                this.cancel();
                                return;
                            }
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
                                    int harmlevel = p.getHarmLevel();
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
            if (p.getGravity() > 1) {
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, p.getGravity() - 1));
                e.getPlayer()
                        .addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 99999, p.getGravity() - 1));
            }
        }
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        e.getPlayer().removePotionEffect(PotionEffectType.JUMP);
        e.getPlayer().removePotionEffect(PotionEffectType.SLOW_FALLING);

        Planet p = PlanetUtils.getPlanet(e.getPlayer().getWorld());
        if (p != null) {
            if (!p.getHabitable()) {
                if (ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getHelmet())
                        && ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getChestplate())
                        && ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getLeggings())
                        && ClayTechManager.isSpaceSuit(e.getPlayer().getInventory().getBoots())) {
                    World PreviousWorld = e.getPlayer().getWorld();
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            if (!PreviousWorld.equals(e.getPlayer().getWorld()) || !e.getPlayer().isOnline()) {
                                this.cancel();
                                return;
                            }
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
                            if (!PreviousWorld.equals(e.getPlayer().getWorld()) || !e.getPlayer().isOnline()) {
                                this.cancel();
                                return;
                            }
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
                                    int harmlevel = p.getHarmLevel();
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
                            if (!PreviousWorld.equals(e.getPlayer().getWorld()) || !e.getPlayer().isOnline()) {
                                this.cancel();
                                return;
                            }
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
                                    int harmlevel = p.getHarmLevel();
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
            if (p.getGravity() > 1) {
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, p.getGravity() - 1));
                e.getPlayer()
                        .addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 99999, p.getGravity() - 1));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void EntityPotionEffectEvent(EntityPotionEffectEvent e) {
        if (e.getCause() == Cause.MILK && e.getEntity() instanceof Player) {
            Planet p = PlanetUtils.getPlanet(e.getEntity().getWorld());
            if (p == null) return;
            if (p.getGravity() != 1) {
                e.setCancelled(true);
                e.getEntity().sendMessage(Lang.readGeneralText("Cant_Drink_Milk"));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerTeleportEvent(PlayerTeleportEvent e) {
        if (e.getPlayer().hasPermission("claytech.bypasstpcheck")) return;

        Planet p = PlanetUtils.getPlanet(e.getPlayer().getWorld());
        Planet to = PlanetUtils.getPlanet(e.getTo().getWorld());
        String inRocket = "false";
        if (p != null) {
            // 在一个星球上
            if (to != null) {
                if (p.getPlanetWorldName().equalsIgnoreCase(to.getPlanetWorldName())) {
                    // 如果目标位置在一个星球
                    return;
                }
                // 否则，目标位置在另外一个星球.
                if (Utils.readPlayerMetadataString(e.getPlayer(), "inrocket") != null) {
                    inRocket = Utils.readPlayerMetadataString(e.getPlayer(), "inrocket");
                }
                boolean ast = Utils.readPlayerMetadataBoolean(e.getPlayer(), "allowSpaceTeleport");
                if (!inRocket.equalsIgnoreCase("true")) {
                    if (ast) {
                        e.getPlayer().setMetadata("allowSpaceTeleport",
                                new FixedMetadataValue(ClayTech.getInstance(), false));
                        return;
                    }
                    // 其他星球传送到主世界
                    e.getPlayer().sendMessage(Lang.readGeneralText("CantUseOtherTeleportInUniverse"));
                    e.setCancelled(true);
                    return;
                }
            } else {
                // 再否则，目标位置不在任何星球。
                // 比如，月球传送到地狱。
                boolean ast = Utils.readPlayerMetadataBoolean(e.getPlayer(), "allowSpaceTeleport");
                if (!p.getPlanetWorldName().equalsIgnoreCase(ClayTech.getOverworld())) {
                    if (ast) {
                        e.getPlayer().setMetadata("allowSpaceTeleport",
                                new FixedMetadataValue(ClayTech.getInstance(), false));
                        return;
                    }
                    // 其他星球传送到主世界
                    e.getPlayer().sendMessage(Lang.readGeneralText("CantUseOtherTeleportInUniverse"));
                    e.setCancelled(true);
                    return;
                }
            }
        } else if (to != null) {
            // 目标位置是一个星球，但出发位置不是任何一个星球。
            boolean ast = Utils.readPlayerMetadataBoolean(e.getPlayer(), "allowSpaceTeleport");
            if (!to.getPlanetWorldName().equalsIgnoreCase(ClayTech.getOverworld())) {
                if (ast) {
                    e.getPlayer().setMetadata("allowSpaceTeleport",
                            new FixedMetadataValue(ClayTech.getInstance(), false));
                    return;
                }
                // 在主世界传送到其他星球
                e.getPlayer().sendMessage(Lang.readGeneralText("CantUseOtherTeleportInUniverse"));
                e.setCancelled(true);
                return;
            }
        }
        // 最后否则，出发地和结束地都不在任何一个星球，pass掉.
    }

    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent e) {
        if (e.getEntityType() == EntityType.PLAYER && e.getCause() == DamageCause.FALL) {
            Player p = (Player) e.getEntity();
            if (ClayTechManager.isSpaceSuit(p.getInventory().getHelmet())
                    && ClayTechManager.isSpaceSuit(p.getInventory().getChestplate())
                    && ClayTechManager.isSpaceSuit(p.getInventory().getLeggings())
                    && ClayTechManager.isSpaceSuit(p.getInventory().getBoots())) {
                e.setDamage(e.getDamage() - e.getFinalDamage());
                if (Utils.readPlayerMetadataBoolean(p, "SpaceSuitNoCostDurability")) {
                    e.setCancelled(true);
                    p.setMetadata("SpaceSuitNoCostDurability", new FixedMetadataValue(ClayTech.getInstance(), false));
                }
                p.sendMessage(Lang.readGeneralText("SpaceSuitFall"));
            }
        }
    }

    @EventHandler
    public void EntityDeathEvent(EntityDeathEvent ev) {
        EntityDamageEvent e = ev.getEntity().getLastDamageCause();
        if (e == null) return;
        if (e.getEntityType() == EntityType.PLAYER && e.getCause() == DamageCause.FALL) {
            Player p = (Player) e.getEntity();
            if (ClayTechManager.isSpaceSuit(p.getInventory().getHelmet())
                    && ClayTechManager.isSpaceSuit(p.getInventory().getChestplate())
                    && ClayTechManager.isSpaceSuit(p.getInventory().getLeggings())
                    && ClayTechManager.isSpaceSuit(p.getInventory().getBoots())) {
                e.setDamage(e.getDamage() - e.getFinalDamage());
                if (Utils.readPlayerMetadataBoolean(p, "SpaceSuitNoCostDurability")) {
                    e.setCancelled(true);
                    p.setMetadata("SpaceSuitNoCostDurability", new FixedMetadataValue(ClayTech.getInstance(), false));
                }
                p.sendMessage(Lang.readGeneralText("SpaceSuitFall"));
            }
        }
    }

    @EventHandler
    public void PlayerBucketEmptyEvent(PlayerBucketEmptyEvent e) {
        // 禁止玩家放置液体
        Planet p = PlanetUtils.getPlanet(e.getBlock().getWorld());
        if (p != null) {
            if (p.getCold()) {
                if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.WATER_BUCKET) {
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            e.getBlock().getWorld().playSound(e.getBlock().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH,
                                    1.0F, 1.0F);
                            e.getBlock().setType(Material.BLUE_ICE);
                        }

                    }.runTaskLater(ClayTech.getInstance(), 30);
                    return;
                }
                if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.LAVA_BUCKET) {
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            e.getBlock().getWorld().playSound(e.getBlock().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH,
                                    1.0F, 1.0F);
                            e.getBlock().setType(Material.OBSIDIAN);
                        }

                    }.runTaskLater(ClayTech.getInstance(), 30);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void BlockDispenseEvent(BlockDispenseEvent e) {
        // 禁止发射器放置液体
        Planet p = PlanetUtils.getPlanet(e.getBlock().getWorld());
        if (p != null) {
            if (p.getCold()) {
                if (e.getItem().getType() == Material.WATER_BUCKET) {
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            Dispenser d = (Dispenser) e.getBlock().getBlockData();
                            Block targetBlock = e.getBlock().getRelative(d.getFacing());
                            e.getBlock().getWorld().playSound(e.getBlock().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH,
                                    1.0F, 1.0F);
                            targetBlock.setType(Material.BLUE_ICE);
                        }

                    }.runTaskLater(ClayTech.getInstance(), 30);
                    return;
                }
                if (e.getItem().getType() == Material.LAVA_BUCKET) {
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            Dispenser d = (Dispenser) e.getBlock().getBlockData();
                            Block targetBlock = e.getBlock().getRelative(d.getFacing());
                            e.getBlock().getWorld().playSound(e.getBlock().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH,
                                    1.0F, 1.0F);
                            targetBlock.setType(Material.OBSIDIAN);
                        }

                    }.runTaskLater(ClayTech.getInstance(), 30);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void CauldronLevelChangeEvent(CauldronLevelChangeEvent e) {
        Planet p = PlanetUtils.getPlanet(e.getBlock().getWorld());
        if (p != null) {
            if (p.getCold()) {
                e.getBlock().getWorld().playSound(e.getBlock().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
                e.setNewLevel(0);
                return;
            }
        }
    }
}
