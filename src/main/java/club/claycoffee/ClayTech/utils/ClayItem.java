package club.claycoffee.ClayTech.utils;

import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ClayItem {
	public static boolean hasDurability(ItemStack item) {
		for(String each : Utils.getLoreList(item)) {
			if(each.startsWith("§b"+Lang.readGeneralText("Durability"))) {
				return true;
			}
		}
		return false;
	}
	public static int getDurability(ItemStack item) {
		if(hasDurability(item)) {
			for(String each : Utils.getLoreList(item)) {
				if(each.startsWith("§b"+Lang.readGeneralText("Durability"))) {
					return new Integer(each.replaceFirst("§b"+Lang.readGeneralText("Durability")+":", "").replace(" §6", "")).intValue();
				}
			}
		}
		else {
			return -1;
		}
		return -1;
	}
	public static boolean setDurability(ItemStack item,int durability) {
		if(hasDurability(item)) {
			if(durability <= 0) {
				item.setAmount(0);
				return true;
			}
			List<String> Lore = Utils.getLoreList(item);
			int i = 0;
			for(String each : Lore) {
				if(each.startsWith("§b"+Lang.readGeneralText("Durability"))) {
					each = each.replace("§6"+getDurability(item), "§6"+durability);
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
