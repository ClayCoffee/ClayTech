package club.claycoffee.ClayTech.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.TRecipe;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class PotionAffect_Weapons {
	public PotionAffect_Weapons() {
		ItemStack[] recipeh = { new ItemStack(Material.INK_SAC), new ItemStack(Material.END_CRYSTAL),
				new ItemStack(Material.INK_SAC), new ItemStack(Material.INK_SAC), Defines.MAGIC_CLAY,
				new ItemStack(Material.INK_SAC), new ItemStack(Material.INK_SAC), new ItemStack(Material.END_CRYSTAL),
				new ItemStack(Material.INK_SAC) };
		ItemStack[] recipei = { Defines.ADVANCED_BLIND_CORE, Defines.ADVANCED_BLIND_CORE, Defines.ADVANCED_BLIND_CORE,
				Defines.ADVANCED_BLIND_CORE, new ItemStack(Material.DIAMOND_SWORD), Defines.ADVANCED_BLIND_CORE,
				Defines.ADVANCED_BLIND_CORE, Defines.ADVANCED_BLIND_CORE, Defines.ADVANCED_BLIND_CORE };
		ItemStack[] poison_eye = { new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE),
				new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE), Defines.MAGIC_CLAY,
				new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE),
				new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE) };
		ItemStack[] poison_core = { new ItemStack(Material.COAL_BLOCK), Defines.POISON_EYE,  new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.FERMENTED_SPIDER_EYE),
				new ItemStack(Material.FERMENTED_SPIDER_EYE), new ItemStack(Material.FERMENTED_SPIDER_EYE),  new ItemStack(Material.COAL_BLOCK),
				Defines.POISON_EYE,  new ItemStack(Material.COAL_BLOCK), Defines.POISON_EYE };
		ItemStack[] confusion_core = { new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH),
				new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH), Defines.MAGIC_CLAY,
				new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH),
				new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH) };
		ItemStack[] black_rock_block = { new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN),
				new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN), Defines.MAGIC_CLAY,
				new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN),
				new ItemStack(Material.OBSIDIAN) };
		ItemStack[] slowness_core = { Defines.BLACK_ROCK_BLOCK, Defines.BLACK_ROCK_BLOCK, Defines.BLACK_ROCK_BLOCK,
				Defines.BLACK_ROCK_BLOCK, Defines.MAGIC_CLAY, Defines.BLACK_ROCK_BLOCK, Defines.BLACK_ROCK_BLOCK,
				Defines.BLACK_ROCK_BLOCK, Defines.BLACK_ROCK_BLOCK };
		ItemStack[] advanced_confusion_core = { Defines.CONFUSION_CORE, Defines.CONFUSION_CORE, Defines.CONFUSION_CORE,
				Defines.CONFUSION_CORE, Defines.MAGIC_CLAY, Defines.CONFUSION_CORE, Defines.CONFUSION_CORE,
				Defines.CONFUSION_CORE, Defines.CONFUSION_CORE };
		ItemStack[] advanced_poison_core = { Defines.POISON_CORE, Defines.POISON_CORE, Defines.POISON_CORE,
				Defines.POISON_CORE, Defines.MAGIC_CLAY, Defines.POISON_CORE, Defines.POISON_CORE, Defines.POISON_CORE,
				Defines.POISON_CORE };
		ItemStack[] advanced_slowness_core = { Defines.SLOWNESS_CORE, Defines.SLOWNESS_CORE, Defines.SLOWNESS_CORE,
				Defines.SLOWNESS_CORE, Defines.MAGIC_CLAY, Defines.SLOWNESS_CORE, Defines.SLOWNESS_CORE,
				Defines.SLOWNESS_CORE, Defines.SLOWNESS_CORE };
		ItemStack[] advanced_blind_core = { Defines.BLIND_CORE, Defines.BLIND_CORE, Defines.BLIND_CORE,
				Defines.BLIND_CORE, Defines.MAGIC_CLAY, Defines.BLIND_CORE, Defines.BLIND_CORE, Defines.BLIND_CORE,
				Defines.BLIND_CORE };
		ItemStack[] four_bow = {  new ItemStack(Material.COAL_BLOCK), Defines.ADVANCED_BLIND_CORE,  new ItemStack(Material.COAL_BLOCK), Defines.ADVANCED_CONFUSION_CORE,
				new ItemStack(Material.BOW), Defines.ADVANCED_POISON_CORE,  new ItemStack(Material.COAL_BLOCK), Defines.ADVANCED_SLOWNESS_CORE, new ItemStack(Material.COAL_BLOCK) };
		ItemStack[] poison_sword = { Defines.ADVANCED_POISON_CORE, Defines.ADVANCED_POISON_CORE,
				Defines.ADVANCED_POISON_CORE, Defines.ADVANCED_POISON_CORE, new ItemStack(Material.DIAMOND_SWORD),
				Defines.ADVANCED_POISON_CORE, Defines.ADVANCED_POISON_CORE, Defines.ADVANCED_POISON_CORE,
				Defines.ADVANCED_POISON_CORE };

		Slimefunutils.registerItem(Defines.C_MATERIALS, "BLIND_CORE", Defines.BLIND_CORE, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, recipeh, false);
		Slimefunutils.registerItem(Defines.C_MATERIALS, "ADVANCED_BLIND_CORE", Defines.ADVANCED_BLIND_CORE,
				"notresearch", 10, RecipeType.ANCIENT_ALTAR, advanced_blind_core, false);
		Slimefunutils.registerItem(Defines.C_MATERIALS, "POISON_CORE", Defines.POISON_CORE, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, poison_core, false);
		Slimefunutils.registerItem(Defines.C_MATERIALS, "ADVANCED_POISON_CORE", Defines.ADVANCED_POISON_CORE,
				"notresearch", 10, RecipeType.ANCIENT_ALTAR, advanced_poison_core, false);
		Slimefunutils.registerItem(Defines.C_MATERIALS, "CONFUSION_CORE", Defines.CONFUSION_CORE, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, confusion_core, false);
		Slimefunutils.registerItem(Defines.C_MATERIALS, "ADVANCED_CONFUSION_CORE", Defines.ADVANCED_CONFUSION_CORE,
				"notresearch", 10, RecipeType.ANCIENT_ALTAR, advanced_confusion_core, false);
		Slimefunutils.registerItem(Defines.C_MATERIALS, "SLOWNESS_CORE", Defines.SLOWNESS_CORE, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, slowness_core, false);
		Slimefunutils.registerItem(Defines.C_MATERIALS, "ADVANCED_SLOWNESS_CORE", Defines.ADVANCED_SLOWNESS_CORE,
				"notresearch", 10, RecipeType.ANCIENT_ALTAR, advanced_slowness_core, false);

		Slimefunutils.registerItem(Defines.C_MATERIALS, "POISON_EYE", Defines.POISON_EYE, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, poison_eye, false);
		Slimefunutils.registerItem(Defines.C_MATERIALS, "BLACK_ROCK_BLOCK", Defines.BLACK_ROCK_BLOCK, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, black_rock_block, false);

		Slimefunutils.registerItem(Defines.C_WEAPONS, "BLIND_SWORD", Defines.BLIND_SWORD, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, recipei, false);
		Slimefunutils.registerItem(Defines.C_WEAPONS, "FOUR_BOW", Defines.FOUR_BOW, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, four_bow, false);
		Slimefunutils.registerItem(Defines.C_WEAPONS, "POISON_SWORD", Defines.POISON_SWORD, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, poison_sword, false);

		Research weapon_requires = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_EX_BASIC_WEAPONS"), 9904,
				Lang.readResearchesText("CLAYTECH_BEFORE_AFFECT_WEAPONS"), 50);
		weapon_requires.addItems(SlimefunItem.getByItem(Defines.BLIND_CORE),
				SlimefunItem.getByItem(Defines.CONFUSION_CORE), SlimefunItem.getByItem(Defines.SLOWNESS_CORE),
				SlimefunItem.getByItem(Defines.POISON_CORE),
				SlimefunItem.getByItem(Defines.POISON_EYE),
				SlimefunItem.getByItem(Defines.BLACK_ROCK_BLOCK));
		weapon_requires.register();

		Research weapons_basic = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_BASIC_WEAPONS"), 9905,
				Lang.readResearchesText("CLAYTECH_AFFECT_WEAPONS"), 50);
		weapons_basic.addItems(SlimefunItem.getByItem(Defines.BLIND_SWORD),
				SlimefunItem.getByItem(Defines.POISON_SWORD));
		weapons_basic.register();

		Research advancedweapon_requires = new Research(
				new NamespacedKey(ClayTech.plugin, "CLAYTECH_EX_ADVANCED_WEAPONS"), 9906, Lang.readResearchesText("CLAYTECH_BEFORE_ADVANCED_AFFECT_WEAPONS"), 75);
		advancedweapon_requires.addItems(SlimefunItem.getByItem(Defines.ADVANCED_BLIND_CORE),
				SlimefunItem.getByItem(Defines.ADVANCED_CONFUSION_CORE),
				SlimefunItem.getByItem(Defines.ADVANCED_SLOWNESS_CORE),
				SlimefunItem.getByItem(Defines.ADVANCED_POISON_CORE));
		advancedweapon_requires.register();

		Research advancedweapon = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_ADVANCED_WEAPONS"), 9906,
				Lang.readResearchesText("CLAYTECH_ADVANCED_AFFECT_WEAPONS"), 100);
		advancedweapon.addItems(SlimefunItem.getByItem(Defines.FOUR_BOW));
		advancedweapon.register();
	}
}
