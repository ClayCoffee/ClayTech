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
				RecipeType.MAGIC_WORKBENCH, recipea, false);
		Slimefunutils.registerItem(ClayTechItems.C_BASICS, "CLAY_STICK", ClayTechItems.CLAY_STICK, "notresearch", 10,
				RecipeType.MAGIC_WORKBENCH, recipeb, false);

		Research basic = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_BASIC"), 9900,
				Lang.readResearchesText("CLAYTECH_START"), 20);
		basic.addItems(SlimefunItem.getByItem(ClayTechItems.MAGIC_CLAY),
				SlimefunItem.getByItem(ClayTechItems.CLAY_STICK));
		basic.register();

		Research basic2 = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_ELECBASIC"), 9915,
				Lang.readResearchesText("CLAYTECH_ELECTRICMACHINE"), 65);
		basic2.addItems(SlimefunItem.getByItem(ClayTechItems.CLAY_CRAFTING_TABLE),
				SlimefunItem.getByItem(ClayTechItems.CLAY_ELECTRIC_STONE_CRUSHER),
				SlimefunItem.getByItem(ClayTechItems.CLAY_FOOD_CAULDRON),
				SlimefunItem.getByItem(ClayTechItems.CLAY_FOOD_CHALKING_MACHINE));
		basic2.register();

		Research basic3 = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_ELEMACHINE"), 9918,
				Lang.readResearchesText("CLAYTECH_ELEMENT_EXTRACTER_MACHINE"), 65);
		basic3.addItems(SlimefunItem.getByItem(ClayTechItems.CLAY_ELEMENT_EXTRACTER));
		basic3.register();
	}
}
