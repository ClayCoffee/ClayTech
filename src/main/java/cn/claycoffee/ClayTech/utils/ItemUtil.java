package cn.claycoffee.ClayTech.utils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemUtil {
    public static ItemStack setDisplayName(ItemStack item, String newDisplayName) {
        ItemStack ret = item.clone();
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(newDisplayName);
        ret.setItemMeta(im);
        return ret;
    }

    public static ItemStack setLore(ItemStack item, String[] newLore) {
        ItemStack ret = item.clone();
        ItemMeta im = item.getItemMeta();
        im.setLore(Arrays.asList(newLore));
        ret.setItemMeta(im);
        return ret;
    }

    public static ItemStack setLore(ItemStack item, List<String> newLore) {
        ItemStack ret = item.clone();
        ItemMeta im = item.getItemMeta();
        im.setLore(newLore);
        ret.setItemMeta(im);
        return ret;
    }

    public static ItemStack setInfo(ItemStack item, String displayName, List<String> lore) {
        ItemStack ret = item.clone();
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(displayName);
        im.setLore(lore);
        ret.setItemMeta(im);
        return ret;
    }

    public static ItemStack setInfo(ItemStack item, String displayName, String[] lore) {
        ItemStack ret = item.clone();
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(displayName);
        im.setLore(Arrays.asList(lore));
        ret.setItemMeta(im);
        return ret;
    }

    public static ItemStack addEnchantment(ItemStack item, Enchantment ench, int lvl) {
        ItemStack ret = item.clone();
        ret.addUnsafeEnchantment(ench, lvl);
        return ret;
    }
}
