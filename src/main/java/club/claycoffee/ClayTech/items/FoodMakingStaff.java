package club.claycoffee.ClayTech.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class FoodMakingStaff {
	public FoodMakingStaff(){
		ItemStack[] recipea = {null,null,null,null,new ItemStack(Material.CHICKEN),null,null,null,null};
		ItemStack[] recipec = {null,new ItemStack(Material.BREAD),null,null,Defines.MAGIC_CLAY,null,null,new ItemStack(Material.BREAD),null};
		ItemStack[] reciped = {null,new ItemStack(Material.KELP),null,null,Defines.MAGIC_CLAY,null,null,new ItemStack(Material.BAMBOO),null};
		
		Slimefunutils.registerItem(Defines.C_FOODMATERIALS, "RAW_CHICKEN_FOOT", Defines.RAW_CHICKEN_FOOT, "notresearch", 10,
				RecipeType.ORE_CRUSHER, recipea, false);
		Slimefunutils.registerItem(Defines.C_FOODMATERIALS, "RAW_BREAD", Defines.RAW_BREAD, "notresearch", 10,
				RecipeType.ENHANCED_CRAFTING_TABLE, recipec, false);
		Slimefunutils.registerItem(Defines.C_FOODMATERIALS, "RAW_VEGETABLE", Defines.RAW_VEGETABLE, "notresearch", 10,
				RecipeType.ENHANCED_CRAFTING_TABLE, reciped, false);
		Slimefunutils.registerItem(Defines.C_FOODMATERIALS, "LEMON", Defines.LEMON, "notresearch", 10,
				RecipeType.NULL, Defines.NORECIPE, false);
		Slimefunutils.registerItem(Defines.C_FOODMATERIALS, "STARCH", Defines.STARCH, "notresearch", 10,
				RecipeType.NULL, Defines.NORECIPE, false);
		Slimefunutils.registerItem(Defines.C_FOODMATERIALS, "FLOUR", Defines.FLOUR, "notresearch", 10,
				RecipeType.NULL, Defines.NORECIPE, false);
		Slimefunutils.registerItem(Defines.C_FOODMATERIALS, "SNAIL_HEALTHY", Defines.SNAIL_HEALTHY, "notresearch", 10,
				RecipeType.NULL, Defines.NORECIPE, false);
		
		Research foodmaterialsI = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_FOODMATERIALSBASIC"), 9910, "制作食物的材料 I", 50);
		foodmaterialsI.addItems(SlimefunItem.getByItem(Defines.RAW_CHICKEN_FOOT),SlimefunItem.getByItem(Defines.RAW_BREAD),SlimefunItem.getByItem(Defines.RAW_VEGETABLE));
		foodmaterialsI.register();
		
		Research foodmaterialsIE = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_FOODMATERIALSBASIC"), 9913, "制作食物的材料 II", 50);
		foodmaterialsIE.addItems(SlimefunItem.getByItem(Defines.FLOUR),SlimefunItem.getByItem(Defines.STARCH),SlimefunItem.getByItem(Defines.SNAIL_HEALTHY));
		foodmaterialsIE.register();
		
		Research foodmaterialsII = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_FRUITBASIC"), 9912, "水果 I", 50);
		foodmaterialsII.addItems(SlimefunItem.getByItem(Defines.LEMON));
		foodmaterialsII.register();
	}
}
