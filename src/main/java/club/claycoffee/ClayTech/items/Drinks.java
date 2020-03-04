package club.claycoffee.ClayTech.items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.TRecipe;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class Drinks {
	public Drinks() {
		ItemStack[] recipea = {null,Defines.COCOA_BEAN,null,null,Defines.COCOA_BEAN,null,null,Defines.DRINK_BOTTLE,null};
		ItemStack[] recipeb = {null,Defines.LEMON_POWDER,null,null,Defines.LEMON_POWDER,null,null,Defines.DRINK_BOTTLE,null};
		ItemStack[] recipec = {null,Defines.TEA_POWDER,null,null,Defines.TEA_POWDER,null,null,Defines.DRINK_BOTTLE,null};
		ItemStack[] reciped = {null,Defines.TEA_POWDER,null,Defines.LEMON_POWDER,Defines.TEA_POWDER,Defines.LEMON_POWDER,null,Defines.DRINK_BOTTLE,null};
		
		Slimefunutils.registerItem(Defines.C_DRINK, "CLAY_COFFEE", Defines.CLAY_COFFEE, "notresearch", 10,
				TRecipe.CLAY_FOOD_CAULDRON, recipea, false);
		Slimefunutils.registerItem(Defines.C_DRINK, "LEMON_POWDER_DRINK", Defines.LEMON_POWDER_DRINK, "notresearch", 10,
				TRecipe.CLAY_FOOD_CAULDRON, recipeb, false);
		Slimefunutils.registerItem(Defines.C_DRINK, "TEA_DRINK", Defines.TEA_DRINK, "notresearch", 10,
				TRecipe.CLAY_FOOD_CAULDRON, recipec, false);
		Slimefunutils.registerItem(Defines.C_DRINK, "LEMON_TEA_DRINK", Defines.LEMON_TEA_DRINK, "notresearch", 10,
				TRecipe.CLAY_FOOD_CAULDRON, reciped, false);

		Research foodI = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_DRINKBASIC"), 9909, "这是??饮料!!", 50);
		foodI.addItems(SlimefunItem.getByItem(Defines.CLAY_COFFEE),SlimefunItem.getByItem(Defines.LEMON_POWDER_DRINK),SlimefunItem.getByItem(Defines.TEA_DRINK),SlimefunItem.getByItem(Defines.LEMON_TEA_DRINK));
		foodI.register();
	}
}
