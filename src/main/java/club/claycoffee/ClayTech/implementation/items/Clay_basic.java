package club.claycoffee.ClayTech.implementation.items;

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

public class Clay_basic {
	public Clay_basic() {
		ItemStack endingot2 = new ItemStack(Material.COAL);

		ItemStack[] recipea = { endingot2, new ItemStack(Material.IRON_INGOT), endingot2,
				new ItemStack(Material.IRON_INGOT), new ItemStack(Material.CLAY), new ItemStack(Material.IRON_INGOT),
				endingot2, new ItemStack(Material.IRON_INGOT), endingot2 };
		ItemStack[] recipeb = { endingot2, new ItemStack(Material.IRON_INGOT), endingot2, new ItemStack(Material.DIRT),
				new ItemStack(Material.STICK), new ItemStack(Material.DIRT), endingot2,
				new ItemStack(Material.IRON_INGOT), endingot2 };

		Slimefunutils.registerItem(ClayTechItems.C_BASICS, "MAGIC_CLAY", ClayTechItems.MAGIC_CLAY, "notresearch", 10,
				RecipeType.ENHANCED_CRAFTING_TABLE, recipea, false);
		Slimefunutils.registerItem(ClayTechItems.C_BASICS, "CLAY_STICK", ClayTechItems.CLAY_STICK, "notresearch", 10,
				RecipeType.ENHANCED_CRAFTING_TABLE, recipeb, false);

		Research basic = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_BASIC"), 9900,
				Lang.readResearchesText("CLAYTECH_START"), 20);
		basic.addItems(SlimefunItem.getByItem(ClayTechItems.MAGIC_CLAY),
				SlimefunItem.getByItem(ClayTechItems.CLAY_STICK));
		basic.register();

		Research basic2 = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_ELECBASIC"), 9915,
				Lang.readResearchesText("CLAYTECH_ELECTRICMACHINE"), 65);
		basic2.addItems(SlimefunItem.getByItem(ClayTechItems.CLAY_CRAFTING_TABLE),
				SlimefunItem.getByItem(ClayTechItems.CLAY_ELECTRIC_STONE_CRUSHER),
				SlimefunItem.getByItem(ClayTechItems.CLAY_FOOD_CAULDRON),
				SlimefunItem.getByItem(ClayTechItems.CLAY_FOOD_CHALKING_MACHINE));
		basic2.register();

		Research basic3 = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_ELEMACHINE"), 9918,
				Lang.readResearchesText("CLAYTECH_ELEMENT_EXTRACTER_MACHINE"), 65);
		basic3.addItems(SlimefunItem.getByItem(ClayTechItems.CLAY_ELEMENT_EXTRACTER));
		basic3.register();

		Research basic4 = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_COBBLESTONE_GENERATOR"),
				9932, Lang.readResearchesText("CLAYTECH_COBBLESTONE_GENERATOR"), 65);
		basic4.addItems(SlimefunItem.getByItem(ClayTechItems.CLAY_COBBLESTONE_GENERATOR));
		basic4.register();

		Research basic5 = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_ELECTRIC_WATER_PUMP"), 9933,
				Lang.readResearchesText("CLAYTECH_ELECTRIC_WATER_PUMP"), 65);
		basic5.addItems(SlimefunItem.getByItem(ClayTechItems.CLAY_ELECTRIC_WATER_PUMP));
		basic5.register();
	}
}
