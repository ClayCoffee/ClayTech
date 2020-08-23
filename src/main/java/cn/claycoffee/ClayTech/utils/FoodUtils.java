package cn.claycoffee.ClayTech.utils;

import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.api.events.PlayerDrinkEvent;
import cn.claycoffee.ClayTech.api.events.PlayerEatEvent;
import cn.claycoffee.ClayTech.api.events.PlayerWashEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Random;

public class FoodUtils {
    public static void DrinkCheck(Player p, ItemStack HandItem, ItemStack food, int incraseFoodLevel,
                                  PotionEffect[] PotionEffect) {
        if (HandItem.hasItemMeta()) {
            if (HandItem.getItemMeta().getDisplayName().equals(food.getItemMeta().getDisplayName())) {
                if (p.getFoodLevel() < 20 || PotionEffect.length > 0) {
                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1.0F, 1.0F);
                    Inventory i = p.getInventory();
                    ItemStack HoldItem = HandItem;
                    HoldItem.setAmount(HoldItem.getAmount() - 1);
                    if (Utils.IsItemContain(i, food)) {
                        i.setItem(Utils.GetItemIndex(i, HoldItem), HoldItem);
                    }
                    i.addItem(ClayTechItems.DIRTY_DRINK_BOTTLE);
                    if (p.getFoodLevel() + incraseFoodLevel > 20) {
                        p.setFoodLevel(20);
                        p.setSaturation(p.getSaturation() + (p.getFoodLevel() + incraseFoodLevel - 20));
                    } else {
                        p.setFoodLevel(p.getFoodLevel() + incraseFoodLevel);
                    }
                    for (PotionEffect pe : PotionEffect) {
                        p.addPotionEffect(pe);
                    }
                    p.sendMessage(Lang.readGeneralText("Drink_Message"));
                    Bukkit.getPluginManager().callEvent(new PlayerDrinkEvent(p, HandItem));
                } else {
                    p.sendMessage(Lang.readGeneralText("Cant_Drink_Message"));
                }
            }
        }
    }

    public static void DrinkCheck(Player p, ItemStack HandItem, ItemStack food, int incraseFoodLevel) {
        if (HandItem.hasItemMeta()) {
            if (HandItem.getItemMeta().getDisplayName().equals(food.getItemMeta().getDisplayName())) {
                if (p.getFoodLevel() < 20) {
                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1.0F, 1.0F);
                    Inventory i = p.getInventory();
                    ItemStack HoldItem = HandItem;
                    HoldItem.setAmount(HoldItem.getAmount() - 1);
                    if (Utils.IsItemContain(i, food)) {
                        i.setItem(Utils.GetItemIndex(i, HoldItem), HoldItem);
                    }
                    i.addItem(ClayTechItems.DIRTY_DRINK_BOTTLE);
                    if (p.getFoodLevel() + incraseFoodLevel > 20) {
                        p.setFoodLevel(20);
                        p.setSaturation(p.getSaturation() + (p.getFoodLevel() + incraseFoodLevel - 20));
                    } else {
                        p.setFoodLevel(p.getFoodLevel() + incraseFoodLevel);
                    }
                    p.sendMessage(Lang.readGeneralText("Drink_Message"));
                    Bukkit.getPluginManager().callEvent(new PlayerDrinkEvent(p, HandItem));
                } else {
                    p.sendMessage(Lang.readGeneralText("Cant_Drink_Message"));
                }
            }
        }
    }

    public static void FoodCheck(Player p, ItemStack HandItem, ItemStack food, int incraseFoodLevel,
                                 PotionEffect[] PotionEffect) {
        if (HandItem.hasItemMeta()) {
            if (HandItem.getItemMeta().getDisplayName().equals(food.getItemMeta().getDisplayName())) {
                if (p.getFoodLevel() < 20 || PotionEffect.length > 0) {
                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
                    Inventory i = p.getInventory();
                    ItemStack HoldItem = HandItem;
                    HoldItem.setAmount(HoldItem.getAmount() - 1);
                    if (Utils.IsItemContain(i, food)) {
                        i.setItem(Utils.GetItemIndex(i, HoldItem), HoldItem);
                    }
                    if (p.getFoodLevel() + incraseFoodLevel > 20) {
                        p.setFoodLevel(20);
                        p.setSaturation(p.getSaturation() + (p.getFoodLevel() + incraseFoodLevel - 20));
                    } else {
                        p.setFoodLevel(p.getFoodLevel() + incraseFoodLevel);
                    }
                    for (PotionEffect pe : PotionEffect) {
                        p.addPotionEffect(pe);
                    }
                    p.sendMessage(Lang.readGeneralText("Eat_Message"));
                    Bukkit.getPluginManager().callEvent(new PlayerEatEvent(p, HandItem));
                } else {
                    p.sendMessage(Lang.readGeneralText("Cant_Eat_Message"));
                }
            }
        }
    }

    public static void FoodCheck(Player p, ItemStack HandItem, ItemStack food, int incraseFoodLevel) {
        if (HandItem.hasItemMeta()) {
            if (HandItem.getItemMeta().getDisplayName().equals(food.getItemMeta().getDisplayName())) {
                if (p.getFoodLevel() < 20) {
                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
                    Inventory i = p.getInventory();
                    ItemStack HoldItem = HandItem;
                    HoldItem.setAmount(HoldItem.getAmount() - 1);
                    if (Utils.IsItemContain(i, food)) {
                        i.setItem(Utils.GetItemIndex(i, HoldItem), HoldItem);
                    }
                    if (p.getFoodLevel() + incraseFoodLevel > 20) {
                        p.setFoodLevel(20);
                        p.setSaturation(p.getSaturation() + (p.getFoodLevel() + incraseFoodLevel - 20));
                    } else {
                        p.setFoodLevel(p.getFoodLevel() + incraseFoodLevel);
                    }
                    p.sendMessage(Lang.readGeneralText("Eat_Message"));
                    Bukkit.getPluginManager().callEvent(new PlayerEatEvent(p, HandItem));
                } else {
                    p.sendMessage(Lang.readGeneralText("Cant_Eat_Message"));
                }
            }
        }
    }

    public static void WashCheck(Player p, ItemStack HandItem, ItemStack matchItem, ItemStack cleanItem) {
        try {
            if (HandItem.getItemMeta().getDisplayName().equals(matchItem.getItemMeta().getDisplayName())) {
                Inventory i = p.getInventory();
                if (i.contains(new ItemStack(Material.WATER_BUCKET))) {
                    int itemindex = Utils.GetItemIndex(i, matchItem);
                    int itemindex2 = i.first(new ItemStack(Material.WATER_BUCKET));
                    ItemStack HoldItem = HandItem;
                    HoldItem.setAmount(HoldItem.getAmount() - 1);
                    if (Utils.IsItemContain(i, matchItem)) {
                        i.setItem(itemindex, HoldItem);
                    }
                    ItemStack HoldItem2 = i.getItem(itemindex2);
                    HoldItem2.setAmount(HoldItem2.getAmount() - 1);
                    if (i.contains(new ItemStack(Material.WATER_BUCKET))) {
                        i.setItem(itemindex2, HoldItem2);
                    }
                    i.addItem(new ItemStack(Material.BUCKET));
                    i.addItem(cleanItem);
                    p.sendMessage(Lang.readGeneralText("Wash_Message"));
                    Bukkit.getPluginManager().callEvent(new PlayerWashEvent(p, matchItem, cleanItem));
                } else {
                    p.sendMessage(Lang.readGeneralText("Cant_Wash_Message"));
                }
            }

        } catch (NullPointerException err) {
        }
    }

    public static void CheckDestroy(Player p, Block b, ItemStack targetBlock, ItemStack dropItem, ItemStack disableItem,
                                    int i, BlockBreakEvent b1) {
        if (b.getType() == targetBlock.getType()) {
            boolean pass = false;
            if (p.getInventory().getItemInMainHand() != new ItemStack(Material.AIR)) {
                if (Utils.HasEnchantment(p.getInventory().getItemInMainHand(), Enchantment.SILK_TOUCH)
                        || p.getInventory().getItemInMainHand().getType() == disableItem.getType()) {
                    // b1.setDropItems(true);
                    return;
                } else
                    pass = true;
            } else
                pass = true;
            if (pass) {
                Random r = new Random();
                int random = r.nextInt(100);
                if (random == i) {
                    Utils.dropItems(b.getLocation(), dropItem);
                    b1.setDropItems(false);
                    return;
                }
            }
        }
        // b1.setDropItems(true);
        return;
    }

    public static void CheckDestroy(Player p, Block b, ItemStack targetBlock, ItemStack dropItem, ItemStack disableItem,
                                    int from, int to, BlockBreakEvent b1) {
        if (b.getType() == targetBlock.getType()) {
            boolean pass = false;
            if (p.getInventory().getItemInMainHand() != new ItemStack(Material.AIR)) {
                if (Utils.HasEnchantment(p.getInventory().getItemInMainHand(), Enchantment.SILK_TOUCH)
                        || p.getInventory().getItemInMainHand().getType() == disableItem.getType()) {
                    // b1.setDropItems(true);
                    return;
                } else
                    pass = true;
            } else
                pass = true;
            if (pass) {
                Random r = new Random();
                int random = r.nextInt(100);
                if (random >= from && random <= to) {
                    Utils.dropItems(b.getLocation(), dropItem);
                    b1.setDropItems(false);
                    return;
                }
            }
        }
        // b1.setDropItems(true);
        return;
    }

    public static void CheckDestroy(Player p, Block b, ItemStack targetBlock, ItemStack dropItem, int i,
                                    BlockBreakEvent b1) {
        if (b.getType() == targetBlock.getType()) {
            boolean pass = false;
            if (p.getInventory().getItemInMainHand() != new ItemStack(Material.AIR)) {
                if (Utils.HasEnchantment(p.getInventory().getItemInMainHand(), Enchantment.SILK_TOUCH)) {
                    // b1.setDropItems(true);
                    return;
                } else
                    pass = true;
            } else
                pass = true;
            if (pass) {
                Random r = new Random();
                int random = r.nextInt(100);
                if (random == i) {
                    Utils.dropItems(b.getLocation(), dropItem);
                    b1.setDropItems(false);
                    return;
                }
            }
        }
        // b1.setDropItems(true);
        return;
    }

    public static void FishItemCheck(PlayerFishEvent e, int from, int to, ItemStack drop) {
        Random r = new Random();
        int random = r.nextInt(100);
        // Utils.info(""+random);
        if (random >= from && random <= to) {
            e.setCancelled(true);
            Utils.dropItems(e.getHook().getLocation(), drop);
        }
    }
}
