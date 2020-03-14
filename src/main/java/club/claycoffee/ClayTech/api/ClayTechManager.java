package club.claycoffee.ClayTech.api;

import java.lang.reflect.Field;

import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.utils.ClayItem;
import club.claycoffee.ClayTech.utils.Utils;

/**
 * the ClayTech API manager.
 * 粘土科技API管理器
 */
public class ClayTechManager {
	/**
	 *
	 * @return the ItemStack is ClayTech item or not.
	 */
	public static boolean isClayTechItem(ItemStack item) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = Defines.class.getDeclaredFields();
		for (Field field : fields) {
			ItemStack is = (ItemStack) field.get(new Defines());
			if (Utils.getDisplayName(is).equalsIgnoreCase(Utils.getDisplayName(item))
					&& Utils.getLoreList(is).equals(Utils.getLoreList(item)) && is.getType() == item.getType()) {
				return true;
			}
			else if (Utils.getDisplayName(is).equalsIgnoreCase(Utils.getDisplayName(item))
					&& !Utils.getLoreList(is).equals(Utils.getLoreList(item)) && ClayItem.hasDurability(is) && is.getType() == item.getType()) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
}
