package club.claycoffee.ClayTech.items;

import org.bukkit.NamespacedKey;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.Recipes;
import club.claycoffee.ClayTech.TRecipe;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class PotionAffect_Weapons {
	public PotionAffect_Weapons() {
		Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "BLIND_CORE", ClayTechItems.BLIND_CORE, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, Recipes.BLIND_CORE, false);
		Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ADVANCED_BLIND_CORE", ClayTechItems.ADVANCED_BLIND_CORE,
				"notresearch", 10, RecipeType.ANCIENT_ALTAR, Recipes.ADVANCED_BLIND_CORE, false);
		Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "POISON_CORE", ClayTechItems.POISON_CORE, "notresearch",
				10, TRecipe.CLAY_CRAFTING_TABLE, Recipes.POISON_CORE, false);
		Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ADVANCED_POISON_CORE",
				ClayTechItems.ADVANCED_POISON_CORE, "notresearch", 10, RecipeType.ANCIENT_ALTAR,
				Recipes.ADVANCED_POISON_CORE, false);
		Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "CONFUSION_CORE", ClayTechItems.CONFUSION_CORE,
				"notresearch", 10, TRecipe.CLAY_CRAFTING_TABLE, Recipes.CONFUSION_CORE, false);
		Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ADVANCED_CONFUSION_CORE",
				ClayTechItems.ADVANCED_CONFUSION_CORE, "notresearch", 10, RecipeType.ANCIENT_ALTAR,
				Recipes.ADVANCED_CONFUSION_CORE, false);
		Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "SLOWNESS_CORE", ClayTechItems.SLOWNESS_CORE,
				"notresearch", 10, TRecipe.CLAY_CRAFTING_TABLE, Recipes.SLOWNESS_CORE, false);
		Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ADVANCED_SLOWNESS_CORE",
				ClayTechItems.ADVANCED_SLOWNESS_CORE, "notresearch", 10, RecipeType.ANCIENT_ALTAR,
				Recipes.ADVANCED_SLOWNESS_CORE, false);

		Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "POISON_EYE", ClayTechItems.POISON_EYE, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, Recipes.POISON_EYE, false);
		Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "BLACK_ROCK_BLOCK", ClayTechItems.BLACK_ROCK_BLOCK,
				"notresearch", 10, TRecipe.CLAY_CRAFTING_TABLE, Recipes.BLACK_ROCK_BLOCK, false);

		Slimefunutils.registerItem(ClayTechItems.C_WEAPONS, "BLIND_SWORD", ClayTechItems.BLIND_SWORD, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, Recipes.BLIND_SWORD, false);
		Slimefunutils.registerItem(ClayTechItems.C_WEAPONS, "FOUR_BOW", ClayTechItems.FOUR_BOW, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, Recipes.FOUR_BOW, false);
		Slimefunutils.registerItem(ClayTechItems.C_WEAPONS, "POISON_SWORD", ClayTechItems.POISON_SWORD, "notresearch",
				10, TRecipe.CLAY_CRAFTING_TABLE, Recipes.POISON_SWORD, false);

		Research weapon_requires = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_EX_BASIC_WEAPONS"), 9904,
				Lang.readResearchesText("CLAYTECH_BEFORE_AFFECT_WEAPONS"), 50);
		weapon_requires.addItems(SlimefunItem.getByItem(ClayTechItems.BLIND_CORE),
				SlimefunItem.getByItem(ClayTechItems.CONFUSION_CORE),
				SlimefunItem.getByItem(ClayTechItems.SLOWNESS_CORE), SlimefunItem.getByItem(ClayTechItems.POISON_CORE),
				SlimefunItem.getByItem(ClayTechItems.POISON_EYE),
				SlimefunItem.getByItem(ClayTechItems.BLACK_ROCK_BLOCK));
		weapon_requires.register();

		Research weapons_basic = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_BASIC_WEAPONS"), 9905,
				Lang.readResearchesText("CLAYTECH_AFFECT_WEAPONS"), 50);
		weapons_basic.addItems(SlimefunItem.getByItem(ClayTechItems.BLIND_SWORD),
				SlimefunItem.getByItem(ClayTechItems.POISON_SWORD));
		weapons_basic.register();

		Research advancedweapon_requires = new Research(
				new NamespacedKey(ClayTech.plugin, "CLAYTECH_EX_ADVANCED_WEAPONS"), 9906,
				Lang.readResearchesText("CLAYTECH_BEFORE_ADVANCED_AFFECT_WEAPONS"), 75);
		advancedweapon_requires.addItems(SlimefunItem.getByItem(ClayTechItems.ADVANCED_BLIND_CORE),
				SlimefunItem.getByItem(ClayTechItems.ADVANCED_CONFUSION_CORE),
				SlimefunItem.getByItem(ClayTechItems.ADVANCED_SLOWNESS_CORE),
				SlimefunItem.getByItem(ClayTechItems.ADVANCED_POISON_CORE));
		advancedweapon_requires.register();

		Research advancedweapon = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_ADVANCED_WEAPONS"), 9906,
				Lang.readResearchesText("CLAYTECH_ADVANCED_AFFECT_WEAPONS"), 100);
		advancedweapon.addItems(SlimefunItem.getByItem(ClayTechItems.FOUR_BOW));
		advancedweapon.register();
	}
}
