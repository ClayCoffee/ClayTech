package club.claycoffee.ClayTech.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class Skulls {
	public Skulls() {
		// 合成方式
		ItemStack[] ClayCoffeeHeadRecipe = { new ItemStack(Material.DIAMOND_BLOCK), new ItemStack(Material.GOLD_BLOCK),
				new ItemStack(Material.DIAMOND_BLOCK), Defines.ARTIFICIAL_GOLD_INGOT, Defines.MAGIC_CLAY,
				Defines.ARTIFICIAL_GOLD_INGOT, new ItemStack(Material.DIAMOND_BLOCK),
				new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.DIAMOND_BLOCK) };
		ItemStack[] OtherHeadRecipe = { new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.GOLD_BLOCK),
				new ItemStack(Material.IRON_BLOCK), Defines.ARTIFICIAL_GOLD_NUGGET, Defines.MAGIC_CLAY,
				Defines.ARTIFICIAL_GOLD_NUGGET, new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.GOLD_BLOCK),
				new ItemStack(Material.IRON_BLOCK) };
		ItemStack[] OtherHeadRecipe2 = { new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.GOLD_BLOCK),
				new ItemStack(Material.IRON_BLOCK), Defines.ARTIFICIAL_GOLD_NUGGET, Defines.CLAY_STICK,
				Defines.ARTIFICIAL_GOLD_NUGGET, new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.GOLD_BLOCK),
				new ItemStack(Material.IRON_BLOCK) };
		ItemStack[] ClockRecipe = {new ItemStack(Material.IRON_INGOT),new ItemStack(Material.IRON_INGOT),new ItemStack(Material.IRON_INGOT),new ItemStack(Material.IRON_INGOT),Defines.MAGIC_CLAY,new ItemStack(Material.IRON_INGOT),new ItemStack(Material.IRON_INGOT),new ItemStack(Material.BLACK_DYE),new ItemStack(Material.IRON_INGOT)};
		ItemStack[] LanternRecipe = {new ItemStack(Material.OAK_LOG),new ItemStack(Material.OAK_LOG),new ItemStack(Material.OAK_LOG),new ItemStack(Material.OAK_LOG),new ItemStack(Material.GLOWSTONE),new ItemStack(Material.OAK_LOG),new ItemStack(Material.OAK_LOG),Defines.MAGIC_CLAY,new ItemStack(Material.OAK_LOG)};
		
		// 注册物品
		Slimefunutils.registerItem(Defines.C_DECORATES, "CLAYCOFFEE_HEAD", Defines.CLAYCOFFEE_HEAD, "notresearch", 10,
				RecipeType.ANCIENT_ALTAR, ClayCoffeeHeadRecipe, false);
		Slimefunutils.registerItem(Defines.C_DECORATES, "STALIN_HEAD", Defines.STALIN_HEAD, "notresearch", 10,
				RecipeType.ANCIENT_ALTAR, OtherHeadRecipe, false);
		Slimefunutils.registerItem(Defines.C_DECORATES, "MARX_HEAD", Defines.MARX_HEAD, "notresearch", 10,
				RecipeType.ANCIENT_ALTAR, OtherHeadRecipe2, false);
		Slimefunutils.registerItem(Defines.C_DECORATES, "CLOCK_C", Defines.CLOCK_C, "notresearch", 10,
				RecipeType.ENHANCED_CRAFTING_TABLE, ClockRecipe, false);
		Slimefunutils.registerItem(Defines.C_DECORATES, "LANTERN_C", Defines.LANTERN_C, "notresearch", 10,
				RecipeType.ENHANCED_CRAFTING_TABLE, LanternRecipe, false);

		// 注册研究
		Research skull_basic = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_SKULL_BASIC"), 9907, Lang.readResearchesText("CLAYTECH_SKULL_I"),
				50);
		skull_basic.addItems(SlimefunItem.getByItem(Defines.CLAYCOFFEE_HEAD),
				SlimefunItem.getByItem(Defines.STALIN_HEAD), SlimefunItem.getByItem(Defines.MARX_HEAD));
		skull_basic.register();
		
		Research skull_basic2 = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_SKULL_BASIC"), 9914, Lang.readResearchesText("CLAYTECH_DECORATES_I"),
				50);
		skull_basic2.addItems(SlimefunItem.getByItem(Defines.CLOCK_C),
				SlimefunItem.getByItem(Defines.LANTERN_C));
		skull_basic2.register();
	}
}
