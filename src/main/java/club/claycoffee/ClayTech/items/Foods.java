package club.claycoffee.ClayTech.items;

import org.bukkit.NamespacedKey;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.ClayTechMachineRecipes;
import club.claycoffee.ClayTech.ClayTechRecipeType;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class Foods {
	public Foods() {
		Slimefunutils.registerItem(ClayTechItems.C_FOOD, "CHICKEN_FOOT", ClayTechItems.CHICKEN_FOOT, "notresearch", 10,
				ClayTechRecipeType.CLAY_FOOD_CAULDRON, ClayTechMachineRecipes.CHICKEN_FOOT, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOOD, "SPICY_CHICKEN_BURGER", ClayTechItems.SPICY_CHICKEN_BURGER,
				"notresearch", 10, ClayTechRecipeType.CLAY_FOOD_CAULDRON, ClayTechMachineRecipes.SPICY_CHICKEN_BURGER, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOOD, "BABA_BURGER", ClayTechItems.BABA_BURGER, "notresearch", 10,
				ClayTechRecipeType.CLAY_FOOD_CAULDRON, ClayTechMachineRecipes.BABA_BURGER, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOOD, "CHOCOLATE", ClayTechItems.CHOCOLATE, "notresearch", 10,
				ClayTechRecipeType.CLAY_FOOD_CAULDRON, ClayTechMachineRecipes.CHOCOLATE, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOOD, "SNAIL_BAD", ClayTechItems.SNAIL_BAD, "notresearch", 10,
				RecipeType.NULL, ClayTechItems.NORECIPE, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOOD, "SNAIL_FOOD", ClayTechItems.SNAIL_FOOD, "notresearch", 10,
				ClayTechRecipeType.CLAY_FOOD_CAULDRON, ClayTechMachineRecipes.SNAIL_FOOD, false);
		Slimefunutils.registerItem(ClayTechItems.C_FOOD, "COOKED_SWEET_POTATO", ClayTechItems.COOKED_SWEET_POTATO,
				"notresearch", 10, ClayTechRecipeType.CLAY_FOOD_CAULDRON, ClayTechMachineRecipes.COOKED_SWEET_POTATO, false);

		Research foodI = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_FOODBASIC"), 9911,
				Lang.readResearchesText("CLAYTECH_FOOD_I"), 50);
		foodI.addItems(SlimefunItem.getByItem(ClayTechItems.CHICKEN_FOOT),
				SlimefunItem.getByItem(ClayTechItems.SPICY_CHICKEN_BURGER),
				SlimefunItem.getByItem(ClayTechItems.BABA_BURGER), SlimefunItem.getByItem(ClayTechItems.CHOCOLATE),
				SlimefunItem.getByItem(ClayTechItems.SNAIL_BAD), SlimefunItem.getByItem(ClayTechItems.SNAIL_FOOD));
		foodI.register();

		if (ClayTech.is115) {
			Slimefunutils.registerItem(ClayTechItems.C_FOOD, "HONEY_SWEET", ClayTechItems.HONEY_SWEET, "notresearch",
					10, ClayTechRecipeType.CLAY_FOOD_CAULDRON, ClayTechMachineRecipes.HONEY_SWEET, false);
			Research foodII = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_FOODBASIC2"), 9916,
					Lang.readResearchesText("CLAYTECH_FOOD_II"), 50);
			foodII.addItems(SlimefunItem.getByItem(ClayTechItems.HONEY_SWEET),
					SlimefunItem.getByItem(ClayTechItems.CLAY_SWEET_POTATO),
					SlimefunItem.getByItem(ClayTechItems.COOKED_SWEET_POTATO));
			foodII.register();
		} else {
			Research foodII = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_FOODBASIC2"), 9916,
					Lang.readResearchesText("CLAYTECH_FOOD_II"), 50);
			foodII.addItems(SlimefunItem.getByItem(ClayTechItems.CLAY_SWEET_POTATO),
					SlimefunItem.getByItem(ClayTechItems.COOKED_SWEET_POTATO));
			foodII.register();
		}

	}
}
