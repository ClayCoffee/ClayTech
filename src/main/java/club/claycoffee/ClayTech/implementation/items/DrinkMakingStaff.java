package club.claycoffee.ClayTech.implementation.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.ClayTechMachineRecipes;
import club.claycoffee.ClayTech.ClayTechRecipeType;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class DrinkMakingStaff {
	public DrinkMakingStaff() {
		ItemStack[] recipea = { new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS),
				new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS), ClayTechItems.CLAY_STICK,
				new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS),
				new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS) };
		ItemStack[] recipeb = { null, null, null, null, SlimefunItems.BUCKET_OF_OIL, null, null, null, null };
		ItemStack[] recipec = { ClayTechItems.PLASTIC, ClayTechItems.PLASTIC, ClayTechItems.PLASTIC,
				ClayTechItems.PLASTIC, ClayTechItems.MAGIC_CLAY, ClayTechItems.PLASTIC, ClayTechItems.PLASTIC,
				ClayTechItems.PLASTIC, ClayTechItems.PLASTIC };

		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "COCOA_BEAN", ClayTechItems.COCOA_BEAN, "notresearch",
				10, RecipeType.ENHANCED_CRAFTING_TABLE, recipea, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "PLASTIC", ClayTechItems.PLASTIC, "notresearch", 10,
				RecipeType.ENHANCED_CRAFTING_TABLE, recipeb, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "DRINK_BOTTLE", ClayTechItems.DRINK_BOTTLE,
				"notresearch", 10, RecipeType.ENHANCED_CRAFTING_TABLE, recipec, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "DIRTY_DRINK_BOTTLE",
				ClayTechItems.DIRTY_DRINK_BOTTLE, "notresearch", 10, RecipeType.NULL, ClayTechItems.NORECIPE, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "DIRTY_TEA", ClayTechItems.DIRTY_TEA, "notresearch",
				10, RecipeType.NULL, ClayTechItems.NORECIPE, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "RAW_TEA", ClayTechItems.RAW_TEA, "notresearch", 10,
				RecipeType.NULL, ClayTechItems.NORECIPE, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "TEA_POWDER", ClayTechItems.TEA_POWDER, "notresearch",
				10, ClayTechRecipeType.CLAY_FOOD_CHALKING_MACHINE, ClayTechMachineRecipes.TEA_POWDER, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOODMATERIALS, "LEMON_POWDER", ClayTechItems.LEMON_POWDER,
				"notresearch", 10, ClayTechRecipeType.CLAY_FOOD_CHALKING_MACHINE, ClayTechMachineRecipes.LEMON_POWDER,
				false);

		Research foodmaterialsI = new Research(
				new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_DRINKMATERIALSBASIC"), 9908,
				Lang.readResearchesText("CLAYTECH_DRINK_MAKINGS_I"), 50);
		foodmaterialsI.addItems(SlimefunItem.getByItem(ClayTechItems.COCOA_BEAN),
				SlimefunItem.getByItem(ClayTechItems.PLASTIC), SlimefunItem.getByItem(ClayTechItems.DRINK_BOTTLE),
				SlimefunItem.getByItem(ClayTechItems.DIRTY_DRINK_BOTTLE));
		foodmaterialsI.register();

		Research foodmaterialsII = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_DRINKMATERIALSII"),
				9911, Lang.readResearchesText("CLAYTECH_DRINK_MAKINGS_II"), 50);
		foodmaterialsII.addItems(SlimefunItem.getByItem(ClayTechItems.DIRTY_TEA),
				SlimefunItem.getByItem(ClayTechItems.RAW_TEA), SlimefunItem.getByItem(ClayTechItems.LEMON_POWDER),
				SlimefunItem.getByItem(ClayTechItems.TEA_POWDER));
		foodmaterialsII.register();
	}
}
