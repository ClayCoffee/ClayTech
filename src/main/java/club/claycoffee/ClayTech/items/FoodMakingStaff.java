package club.claycoffee.ClayTech.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class FoodMakingStaff {
	public FoodMakingStaff() {
		ItemStack[] recipea = { null, null, null, null, new ItemStack(Material.CHICKEN), null, null, null, null };
		ItemStack[] recipec = { null, new ItemStack(Material.BREAD), null, null, ClayTechItems.MAGIC_CLAY, null, null,
				new ItemStack(Material.BREAD), null };
		ItemStack[] reciped = { null, new ItemStack(Material.KELP), null, null, ClayTechItems.MAGIC_CLAY, null, null,
				new ItemStack(Material.BAMBOO), null };

		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "RAW_CHICKEN_FOOT", ClayTechItems.RAW_CHICKEN_FOOT, "notresearch",
				10, RecipeType.ORE_CRUSHER, recipea, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "RAW_BREAD", ClayTechItems.RAW_BREAD, "notresearch", 10,
				RecipeType.ENHANCED_CRAFTING_TABLE, recipec, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "RAW_VEGETABLE", ClayTechItems.RAW_VEGETABLE, "notresearch", 10,
				RecipeType.ENHANCED_CRAFTING_TABLE, reciped, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "LEMON", ClayTechItems.LEMON, "notresearch", 10, RecipeType.NULL,
				ClayTechItems.NORECIPE, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "STARCH", ClayTechItems.STARCH, "notresearch", 10,
				RecipeType.NULL, ClayTechItems.NORECIPE, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "FLOUR", ClayTechItems.FLOUR, "notresearch", 10, RecipeType.NULL,
				ClayTechItems.NORECIPE, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "SNAIL_HEALTHY", ClayTechItems.SNAIL_HEALTHY, "notresearch", 10,
				RecipeType.NULL, ClayTechItems.NORECIPE, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "SWEET_POTATO", ClayTechItems.SWEET_POTATO, "notresearch", 10,
				RecipeType.NULL, ClayTechItems.NORECIPE, false);

		Research foodmaterialsI = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_FOODMATERIALSBASIC"), 9910,
				Lang.readResearchesText("CLAYTECH_FOOD_MAKINGS_I"), 50);
		foodmaterialsI.addItems(SlimefunItem.getByItem(ClayTechItems.RAW_CHICKEN_FOOT),
				SlimefunItem.getByItem(ClayTechItems.RAW_BREAD), SlimefunItem.getByItem(ClayTechItems.RAW_VEGETABLE));
		foodmaterialsI.register();

		Research foodmaterialsIE = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_FOODMATERIALSBASIC"), 9913,
				Lang.readResearchesText("CLAYTECH_FOOD_MAKINGS_II"), 50);
		foodmaterialsIE.addItems(SlimefunItem.getByItem(ClayTechItems.FLOUR), SlimefunItem.getByItem(ClayTechItems.STARCH),
				SlimefunItem.getByItem(ClayTechItems.SNAIL_HEALTHY));
		foodmaterialsIE.register();

		Research foodmaterialsII = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_FRUITBASIC"), 9912,
				Lang.readResearchesText("CLAYTECH_FRUIT_I"), 50);
		foodmaterialsII.addItems(SlimefunItem.getByItem(ClayTechItems.LEMON));
		foodmaterialsII.register();
	}
}
