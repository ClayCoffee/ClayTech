package cn.claycoffee.ClayTech.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @Project: ClayAPI
 * @Author: ClayCoffee
 * @Date: 2020/7/29 19:03
 * @Email: 1020757140@qq.com
 * AGPL 3.0
 */

public class MenuUtils {
    public static boolean isSame(GUI m, Inventory i, Player p) {
        if (m.getNowInventory().get(p.getUniqueId().toString()).getHolder() instanceof GUIHolder) {
            GUIHolder holder = (GUIHolder) m.getNowInventory().get(p.getUniqueId().toString()).getHolder();
            if (m.equals(holder.getGUI())) return true;
        }
        return false;
    }

    /*
        注意: page≥1.
     */
    public static void renderNewPage(Inventory source, ItemStack[] stacks, int[] area, int page) {
        int min = (page - 1) * area.length;
        int max = min + area.length - 1;
        int count = 0;
        int num = 0;
        for (int i : area) {
            source.setItem(i, new ItemStack(Material.AIR));
        }

        for (ItemStack e : stacks) {
            if (count >= min && count <= max) {
                source.setItem(area[num], stacks[count]);
                num++;
            }
            count++;
            if (count > max) break;
        }
    }

    /*
        注意: page≥1.
     */
    public static void renderNewPage(Inventory source, List<ItemStack> stacks, int[] area, int page) {
        renderNewPage(source, stacks.toArray(new ItemStack[stacks.size()]), area, page);
    }

    public static int getInternalNumberInStacks(ItemStack stack, List<ItemStack> stacks) {
        if (stack == null) return -1;
        int i = 0;
        for (ItemStack s : stacks) {
            if (s.getItemMeta().getDisplayName().equals(stack.getItemMeta().getDisplayName())) return i;
            i++;
        }
        return -1;
    }
}
