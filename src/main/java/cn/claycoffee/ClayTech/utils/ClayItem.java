package cn.claycoffee.ClayTech.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ClayItem {
    public static boolean hasDurability(ItemStack item) {
        for (String each : Utils.getLoreList(item)) {
            if (each.startsWith(Lang.durability)) {
                return true;
            }
        }
        return false;
    }

    public static int getDurability(ItemStack item) {
        if (hasDurability(item)) {
            for (String each : Utils.getLoreList(item)) {
                if (each.startsWith(Lang.durability)) {
                    return new Integer(
                            each.replaceFirst(Lang.durability + ":", "").replace(" §6", ""))
                            .intValue();
                }
            }
        } else {
            return -1;
        }
        return -1;
    }

    public static boolean setDurability(ItemStack item, int durability) {
        if (hasDurability(item)) {
            if (durability <= 0) {
                item.setAmount(0);
                return true;
            }
            List<String> Lore = Utils.getLoreList(item);
            int i = 0;
            for (String each : Lore) {
                if (each.startsWith(Lang.durability)) {
                    each = Lang.durability + ": §6" + durability;
                    Lore.set(i, each);
                    break;
                }
                i++;
            }
            ItemMeta im = item.getItemMeta();
            im.setLore(Lore);
            item.setItemMeta(im);
            return true;
        }

        return false;
    }
}
