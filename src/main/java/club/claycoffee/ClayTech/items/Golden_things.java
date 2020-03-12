package club.claycoffee.ClayTech.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class Golden_things {
	public Golden_things() {
		ItemStack[] recipec = { new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.GOLD_INGOT),
				new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.GOLD_INGOT), Defines.MAGIC_CLAY,
				new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.GOLD_INGOT),
				new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.GOLD_INGOT),
				new ItemStack(Material.GOLD_INGOT) };
		ItemStack[] reciped = { Defines.ARTIFICIAL_GOLD_NUGGET, Defines.ARTIFICIAL_GOLD_NUGGET,
				Defines.ARTIFICIAL_GOLD_NUGGET, Defines.ARTIFICIAL_GOLD_NUGGET, Defines.MAGIC_CLAY,
				Defines.ARTIFICIAL_GOLD_NUGGET, Defines.ARTIFICIAL_GOLD_NUGGET, Defines.ARTIFICIAL_GOLD_NUGGET,
				Defines.ARTIFICIAL_GOLD_NUGGET };
		ItemStack[] recipee = { null, Defines.MAGIC_CLAY, null, null, Defines.ARTIFICIAL_GOLD_INGOT_O, null, null, null,
				null };
		ItemStack[] recipef = { Defines.ARTIFICIAL_GOLD_INGOT, Defines.ARTIFICIAL_GOLD_INGOT,
				Defines.ARTIFICIAL_GOLD_INGOT, Defines.ARTIFICIAL_GOLD_INGOT, Defines.ARTIFICIAL_GOLD_INGOT,
				Defines.ARTIFICIAL_GOLD_INGOT, Defines.ARTIFICIAL_GOLD_INGOT, Defines.ARTIFICIAL_GOLD_INGOT,
				Defines.ARTIFICIAL_GOLD_INGOT };
		ItemStack[] recipeg = { Defines.ARTIFICIAL_GOLD_BLOCK, Defines.ARTIFICIAL_GOLD_BLOCK,
				Defines.ARTIFICIAL_GOLD_BLOCK, Defines.ARTIFICIAL_GOLD_BLOCK, new ItemStack(Material.GOLDEN_APPLE),
				Defines.ARTIFICIAL_GOLD_BLOCK, Defines.ARTIFICIAL_GOLD_BLOCK, Defines.ARTIFICIAL_GOLD_BLOCK,
				Defines.ARTIFICIAL_GOLD_BLOCK };

		Slimefunutils.registerItem(Defines.C_MATERIALS, "ARTIFICIAL_GOLD_NUGGET", Defines.ARTIFICIAL_GOLD_NUGGET,
				"notresearch", 10, RecipeType.COMPRESSOR, recipec, false);
		Slimefunutils.registerItem(Defines.C_MATERIALS, "ARTIFICIAL_GOLD_INGOT_O", Defines.ARTIFICIAL_GOLD_INGOT_O,
				"notresearch", 10, RecipeType.COMPRESSOR, reciped, false);
		Slimefunutils.registerItem(Defines.C_MATERIALS, "ARTIFICIAL_GOLD_INGOT", Defines.ARTIFICIAL_GOLD_INGOT,
				"notresearch", 10, RecipeType.COMPRESSOR, recipee, false);
		Slimefunutils.registerItem(Defines.C_MATERIALS, "ARTIFICIAL_GOLD_BLOCK", Defines.ARTIFICIAL_GOLD_BLOCK,
				"notresearch", 10, RecipeType.COMPRESSOR, recipef, false);
		Slimefunutils.registerItem(Defines.C_FOOD, "ARTIFICIAL_ENCHANTED_GOLDEN_APPLE",
				Defines.ARTIFICIAL_ENCHANTED_GOLDEN_APPLE, "notresearch", 10, RecipeType.ANCIENT_ALTAR, recipeg, false);

		Research artificial_basic = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_ARTIFICIAL_BASIC"), 9901,
				Lang.readResearchesText("CLAYTECH_ARTIFICIAL_I"), 45);
		artificial_basic.addItems(SlimefunItem.getByItem(Defines.ARTIFICIAL_GOLD_NUGGET),
				SlimefunItem.getByItem(Defines.ARTIFICIAL_GOLD_INGOT),
				SlimefunItem.getByItem(Defines.ARTIFICIAL_GOLD_BLOCK));
		artificial_basic.register();

		Research artificial_basic_o = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_ARTIFICIAL_BASIC_O"),
				9902, Lang.readResearchesText("CLAYTECH_ARTIFICIAL_ORE_I"), 30);
		artificial_basic_o.addItems(SlimefunItem.getByItem(Defines.ARTIFICIAL_GOLD_INGOT_O));
		artificial_basic_o.register();

		Research artificial_enchanted_golden_apple_r = new Research(
				new NamespacedKey(ClayTech.plugin, "CLAYTECH_ENCHANTED_GOLDEN_APPLE"), 9903,
				Lang.readResearchesText("CLAYTECH_ARTIFICIAL_ENCHANTED_GOLDEN_APPLE"), 100);
		artificial_enchanted_golden_apple_r.addItems(SlimefunItem.getByItem(Defines.ARTIFICIAL_ENCHANTED_GOLDEN_APPLE));
		artificial_enchanted_golden_apple_r.register();
	}
}
