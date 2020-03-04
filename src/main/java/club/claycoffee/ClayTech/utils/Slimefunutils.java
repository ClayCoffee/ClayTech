package club.claycoffee.ClayTech.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.Slimefun;

public class Slimefunutils {
	private static int researchId = 9901;

	@SuppressWarnings("deprecation")
	public static void registerItem(Category category, String name, ItemStack ItemStack, String ResearchName, int cost,
			RecipeType Recipetype, ItemStack[] RecipeStack, boolean registerResearch) {
		SlimefunItemStack items = new SlimefunItemStack(name, ItemStack);
		SlimefunItem item = new SlimefunItem(category, items, Recipetype, RecipeStack);
		item.register();
		if (registerResearch) {
			researchId++;
			Slimefun.registerResearch(
					new Research(new NamespacedKey(ClayTech.plugin, name), researchId, ResearchName, cost), ItemStack);
		}
	}
}
