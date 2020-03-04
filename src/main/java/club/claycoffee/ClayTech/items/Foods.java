package club.claycoffee.ClayTech.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.Recipes;
import club.claycoffee.ClayTech.TRecipe;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class Foods {
	public Foods() {
		ItemStack[] recipeb = {null,new ItemStack(Material.COAL),null,null,Defines.RAW_CHICKEN_FOOT,null,null,new ItemStack(Material.COAL),null};
		ItemStack[] recipec = {Defines.RAW_BREAD,Defines.RAW_VEGETABLE,Defines.RAW_BREAD,Defines.RAW_VEGETABLE,Defines.CHICKEN_FOOT,Defines.RAW_VEGETABLE,Defines.RAW_BREAD,Defines.MAGIC_CLAY,Defines.RAW_BREAD};
		ItemStack[] reciped = {new ItemStack(Material.COAL),Defines.RAW_BREAD,new ItemStack(Material.COAL),new ItemStack(Material.COAL),new ItemStack(Material.COAL),new ItemStack(Material.COAL),new ItemStack(Material.COAL),Defines.RAW_BREAD,new ItemStack(Material.COAL)};
		ItemStack[] recipee = {Defines.COCOA_BEAN,Defines.COCOA_BEAN,Defines.COCOA_BEAN,Defines.COCOA_BEAN,Defines.STARCH,Defines.COCOA_BEAN,Defines.COCOA_BEAN,Defines.COCOA_BEAN,Defines.COCOA_BEAN}; 
		ItemStack[] recipef = {Defines.FLOUR,Defines.FLOUR,Defines.FLOUR,Defines.FLOUR,Defines.SNAIL_HEALTHY,Defines.FLOUR,Defines.FLOUR,Defines.FLOUR,Defines.FLOUR};
		
		Slimefunutils.registerItem(Defines.C_FOOD, "CHICKEN_FOOT", Defines.CHICKEN_FOOT, "notresearch", 10,
				TRecipe.CLAY_FOOD_CAULDRON, recipeb, false);
		Slimefunutils.registerItem(Defines.C_FOOD, "SPICY_CHICKEN_BURGER", Defines.SPICY_CHICKEN_BURGER, "notresearch", 10,
				TRecipe.CLAY_FOOD_CAULDRON, recipec, false);
		Slimefunutils.registerItem(Defines.C_FOOD, "BABA_BURGER", Defines.BABA_BURGER, "notresearch", 10,
				TRecipe.CLAY_FOOD_CAULDRON, reciped, false);
		Slimefunutils.registerItem(Defines.C_FOOD, "CHOCOLATE", Defines.CHOCOLATE, "notresearch", 10,
				TRecipe.CLAY_FOOD_CAULDRON, recipee, false);
		Slimefunutils.registerItem(Defines.C_FOOD, "SNAIL_BAD", Defines.SNAIL_BAD, "notresearch", 10,
				RecipeType.NULL, Defines.NORECIPE, false);
		Slimefunutils.registerItem(Defines.C_FOOD, "SNAIL_FOOD", Defines.SNAIL_FOOD, "notresearch", 10,
				TRecipe.CLAY_FOOD_CAULDRON, recipef, false);
		Slimefunutils.registerItem(Defines.C_FOOD, "HONEY_SWEET", Defines.HONEY_SWEET, "notresearch", 10,
				TRecipe.CLAY_FOOD_CAULDRON, Recipes.HONEY_SWEET, false);
		
		Research foodI = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_FOODBASIC"), 9911, "食物 I", 50);
		foodI.addItems(SlimefunItem.getByItem(Defines.CHICKEN_FOOT),SlimefunItem.getByItem(Defines.SPICY_CHICKEN_BURGER),SlimefunItem.getByItem(Defines.BABA_BURGER),SlimefunItem.getByItem(Defines.CHOCOLATE),SlimefunItem.getByItem(Defines.SNAIL_BAD),SlimefunItem.getByItem(Defines.SNAIL_FOOD));
		foodI.register();
		
		Research foodII = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_FOODBASIC2"), 9916, "食物 II", 50);
		foodII.addItems(SlimefunItem.getByItem(Defines.HONEY_SWEET));
		foodII.register();
	}
}
