package cn.claycoffee.ClayTech.implementation.items;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.ClayTechMachineRecipes;
import cn.claycoffee.ClayTech.ClayTechRecipeType;
import cn.claycoffee.ClayTech.api.events.PlayerUseItemEvent;
import cn.claycoffee.ClayTech.listeners.ItemUseListener;
import cn.claycoffee.ClayTech.utils.ClayItem;
import cn.claycoffee.ClayTech.utils.Lang;
import cn.claycoffee.ClayTech.utils.Slimefunutils;
import cn.claycoffee.ClayTech.utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class EffectItems {
    public EffectItems() {
        Slimefunutils.registerItem(ClayTechItems.C_TOOLS, "TNT_EXPLOSION_CREATER", ClayTechItems.TNT_EXPLOSION_CREATER,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.TNT_EXPLOSION_CREATER,
                false, new ItemHandler[]{new ItemUseHandler() {
                    @Override
                    public void onRightClick(PlayerRightClickEvent e) {
                        Bukkit.getPluginManager().callEvent(new PlayerUseItemEvent(e.getPlayer(), e.getItem()));
                        boolean pass = false;
                        String md = Utils.readPlayerMetadataString(e.getPlayer(), "lastUseTNTCreaterTime");
                        if (md != null) {
                            if (System.currentTimeMillis() >= Long.parseLong(md) + 5000L) {
                                pass = true;
                            }
                        } else {
                            pass = true;
                        }
                        if (pass) {
                            if (e.getPlayer().getInventory().containsAtLeast(new ItemStack(Material.TNT), 1)) {
                                Location currentLoc = e.getPlayer().getLocation();
                                Inventory inv = e.getPlayer().getInventory();
                                ItemStack TNT = inv.getItem(inv.first(Material.TNT));
                                TNT.setAmount(TNT.getAmount() - 1);
                                ItemStack tool = e.getPlayer().getInventory().getItemInMainHand();
                                ClayItem.setDurability(tool, ClayItem.getDurability(tool) - 1);
                                e.getPlayer().sendMessage(Lang.readGeneralText("TNT_EXPLOSION_CREATER_WAIT"));
                                e.getPlayer().setMetadata("lastUseTNTCreaterTime",
                                        new FixedMetadataValue(ClayTech.getInstance(), System.currentTimeMillis() + ""));
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        e.getPlayer().getWorld().spawnEntity(currentLoc, EntityType.PRIMED_TNT);
                                        Block center = currentLoc.add(0, -1, 0).getBlock();
                                        center.setMetadata("isExplosionCreater",
                                                new FixedMetadataValue(ClayTech.getInstance(), true));
                                        ItemUseListener.player.put(center, e.getPlayer().getName());
                                        return;
                                    }

                                }.runTaskLater(ClayTech.getInstance(), 100);
                            } else {
                                e.getPlayer().sendMessage(Lang.readGeneralText("TNT_EXPLOSION_CREATER_NO_TNT"));
                                return;
                            }

                        } else {
                            e.getPlayer().sendMessage(Lang.readGeneralText("TNT_EXPLOSION_CREATER_CD"));
                            return;
                        }

                    }
                }});

        Research rs = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_AFT_1"), 9921,
                Lang.readResearchesText("CLAYTECH_EFFECT_ITEM_I"), 30);
        rs.addItems(SlimefunItem.getByItem(ClayTechItems.TNT_EXPLOSION_CREATER));
        rs.register();
    }
}
