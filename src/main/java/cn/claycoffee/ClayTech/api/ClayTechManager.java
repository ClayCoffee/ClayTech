package cn.claycoffee.ClayTech.api;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.utils.ClayItem;
import cn.claycoffee.ClayTech.utils.Lang;
import cn.claycoffee.ClayTech.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.lang.reflect.Field;

/**
 * the ClayTech API manager. 粘土科技API管理器
 */
public class ClayTechManager {
    /**
     * @param item The item.物品.
     * @return the ItemStack is ClayTech item or not. 这个ItemStack是不是粘土科技物品.
     */
    public static boolean isClayTechItem(ItemStack item) {
        Field[] fields = ClayTechItems.class.getDeclaredFields();
        for (Field field : fields) {
            ItemStack is;
            try {
                is = (ItemStack) field.get(new ClayTechItems());
            } catch (IllegalArgumentException e) {
                return false;
            } catch (IllegalAccessException e) {
                return false;
            }
            if (Utils.getDisplayName(is).equalsIgnoreCase(Utils.getDisplayName(item))
                    && Utils.getLoreList(is).equals(Utils.getLoreList(item)) && is.getType() == item.getType()) {
                return true;
            } else if (Utils.getDisplayName(is).equalsIgnoreCase(Utils.getDisplayName(item))
                    && !Utils.getLoreList(is).equals(Utils.getLoreList(item)) && ClayItem.hasDurability(is)
                    && is.getType() == item.getType()) {
                return true;
            } else if (isRocket(is)) {
                return true;
            } else if (isSpaceSuit(is)) {
                return true;
            } else if (isOxygenDistributer(is)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRocket(ItemStack item) {
        if (item == null)
            return false;
        if (item.hasItemMeta()) {
            if (item.getItemMeta().getDisplayName().startsWith(Lang.rocketPrefix)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static boolean isSpaceSuit(ItemStack item) {
        if (item == null)
            return false;
        if (item.hasItemMeta()) {
            if (item.getItemMeta().getDisplayName().startsWith(Lang.spaceSuitPrefix)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static boolean isOxygenDistributer(ItemStack item) {
        if (item == null)
            return false;
        if (item.hasItemMeta()) {
            if (item.getItemMeta().getDisplayName().startsWith(Lang.oxygenDistributerPrefix)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * set a player's spacesuit cost no durability once.(will lose efficacy when the
     * server restarting or reloading)
     */
    public static void setSpaceSuitFallNoCostDurabilityOnce(Player p) {
        p.setMetadata("SpaceSuitNoCostDurability", new FixedMetadataValue(ClayTech.getInstance(), true));
    }

    public static void allowSpaceTeleportOnce(Player p) {
        p.setMetadata("allowSpaceTeleport", new FixedMetadataValue(ClayTech.getInstance(), true));
    }

}
