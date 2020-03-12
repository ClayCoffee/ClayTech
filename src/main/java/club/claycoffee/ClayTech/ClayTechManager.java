package club.claycoffee.ClayTech;

import java.lang.reflect.Field;

import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.utils.Utils;

public class ClayTechManager {
	public static boolean isClayTechItem(ItemStack item) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = Defines.class.getDeclaredFields();
	      for(Field field : fields){
	         ItemStack is = (ItemStack) field.get(new Defines());
	         if(Utils.getDisplayName(is).equalsIgnoreCase(Utils.getDisplayName(item)) && Utils.getLoreList(is).equals(Utils.getLoreList(item))) {
	        	 return true;
	         }
	         else {
	        	 return false;
	         }
	    }
		return false;
	}
}
