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

public class Clay_basic {
	public Clay_basic() {
		ItemStack endingot2 = new ItemStack(Material.COAL);

		ItemStack[] recipea = { endingot2, new ItemStack(Material.IRON_INGOT), endingot2,
				new ItemStack(Material.IRON_INGOT), new ItemStack(Material.CLAY), new ItemStack(Material.IRON_INGOT),
				endingot2, new ItemStack(Material.IRON_INGOT), endingot2 };
		ItemStack[] recipeb = { endingot2, new ItemStack(Material.IRON_INGOT), endingot2, new ItemStack(Material.DIRT),
				new ItemStack(Material.STICK), new ItemStack(Material.DIRT), endingot2,
				new ItemStack(Material.IRON_INGOT), endingot2 };

		Slimefunutils.registerItem(Defines.C_BASICS, "MAGIC_CLAY", Defines.MAGIC_CLAY, "notresearch", 10,
				RecipeType.MAGIC_WORKBENCH, recipea, false);
		Slimefunutils.registerItem(Defines.C_BASICS, "CLAY_STICK", Defines.CLAY_STICK, "notresearch", 10,
				RecipeType.MAGIC_WORKBENCH, recipeb, false);

		Research basic = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_BASIC"), 9900, "嘿!粘土科技,爷来了!", 20);
		basic.addItems(SlimefunItem.getByItem(Defines.MAGIC_CLAY), SlimefunItem.getByItem(Defines.CLAY_STICK));
		basic.register();
		
		Research basic2 = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_ELECBASIC"), 9915, "电器 I", 65);
		basic2.addItems(SlimefunItem.getByItem(Defines.CLAY_CRAFTING_TABLE), SlimefunItem.getByItem(Defines.CLAY_FOOD_CAULDRON), SlimefunItem.getByItem(Defines.CLAY_FOOD_CHALKING_MACHINE));
		basic2.register();
	}
}
