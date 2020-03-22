package club.claycoffee.ClayTech.api;

import java.lang.reflect.Field;

import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.utils.ClayItem;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Utils;

/**
 * the ClayTech API manager. 粘土科技API管理器
 */
public class ClayTechManager {
	/**
	 *
	 * @return the ItemStack is ClayTech item or not. 这个ItemStack是不是粘土科技物品.
	 * @param item The item.物品.
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
			} else if (isOxygenDistributer(is)){
				return true;
			}
		}
		return false;
	}

	public static boolean isRocket(ItemStack item) {
		if (item == null)
			return false;
		if (item.hasItemMeta()) {
			if (item.getItemMeta().getDisplayName().startsWith(Lang.readGeneralText("Rocket"))) {
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
			if (item.getItemMeta().getDisplayName().startsWith(Lang.readGeneralText("SpaceSuit"))) {
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
			if (item.getItemMeta().getDisplayName().startsWith(Lang.readGeneralText("OxygenDistributer"))) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
