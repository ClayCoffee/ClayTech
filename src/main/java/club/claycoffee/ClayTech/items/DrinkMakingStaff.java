package club.claycoffee.ClayTech.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.Recipes;
import club.claycoffee.ClayTech.TRecipe;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class DrinkMakingStaff {
	public DrinkMakingStaff() {
		ItemStack[] recipea = { new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS),
				new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS), Defines.CLAY_STICK,
				new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS),
				new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS) };
		ItemStack[] recipeb = { null, null, null, null, SlimefunItems.BUCKET_OF_OIL, null, null, null, null };
		ItemStack[] recipec = { Defines.PLASTIC, Defines.PLASTIC, Defines.PLASTIC, Defines.PLASTIC, Defines.MAGIC_CLAY,
				Defines.PLASTIC, Defines.PLASTIC, Defines.PLASTIC, Defines.PLASTIC };

		Slimefunutils.registerItem(Defines.C_FOODMATERIALS, "COCOA_BEAN", Defines.COCOA_BEAN, "notresearch", 10,
				RecipeType.ENHANCED_CRAFTING_TABLE, recipea, false);
		Slimefunutils.registerItem(Defines.C_FOODMATERIALS, "PLASTIC", Defines.PLASTIC, "notresearch", 10,
				RecipeType.ENHANCED_CRAFTING_TABLE, recipeb, false);
		Slimefunutils.registerItem(Defines.C_FOODMATERIALS, "DRINK_BOTTLE", Defines.DRINK_BOTTLE, "notresearch", 10,
				RecipeType.ENHANCED_CRAFTING_TABLE, recipec, false);
		Slimefunutils.registerItem(Defines.C_FOODMATERIALS, "DIRTY_DRINK_BOTTLE", Defines.DIRTY_DRINK_BOTTLE,
				"notresearch", 10, RecipeType.NULL, Defines.NORECIPE, false);
		Slimefunutils.registerItem(Defines.C_FOODMATERIALS, "DIRTY_TEA", Defines.DIRTY_TEA, "notresearch", 10,
				RecipeType.NULL, Defines.NORECIPE, false);
		Slimefunutils.registerItem(Defines.C_FOODMATERIALS, "RAW_TEA", Defines.RAW_TEA, "notresearch", 10,
				RecipeType.NULL, Defines.NORECIPE, false);
		Slimefunutils.registerItem(Defines.C_FOODMATERIALS, "TEA_POWDER", Defines.TEA_POWDER, "notresearch", 10,
				TRecipe.CLAY_FOOD_CHALKING_MACHINE, Recipes.TEA_POWDER, false);
		Slimefunutils.registerItem(Defines.C_FOODMATERIALS, "LEMON_POWDER", Defines.LEMON_POWDER, "notresearch", 10,
				TRecipe.CLAY_FOOD_CHALKING_MACHINE, Recipes.LEMON_POWDER, false);

		Research foodmaterialsI = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_DRINKMATERIALSBASIC"), 9908,
				Lang.readResearchesText("CLAYTECH_DRINK_MAKINGS_I"), 50);
		foodmaterialsI.addItems(SlimefunItem.getByItem(Defines.COCOA_BEAN), SlimefunItem.getByItem(Defines.PLASTIC),
				SlimefunItem.getByItem(Defines.DRINK_BOTTLE), SlimefunItem.getByItem(Defines.DIRTY_DRINK_BOTTLE));
		foodmaterialsI.register();

		Research foodmaterialsII = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_DRINKMATERIALSII"), 9911,
				Lang.readResearchesText("CLAYTECH_DRINK_MAKINGS_II"), 50);
		foodmaterialsII.addItems(SlimefunItem.getByItem(Defines.DIRTY_TEA), SlimefunItem.getByItem(Defines.RAW_TEA),
				SlimefunItem.getByItem(Defines.LEMON_POWDER), SlimefunItem.getByItem(Defines.TEA_POWDER));
		foodmaterialsII.register();
	}
}
