package club.claycoffee.ClayTech.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.Slimefun;

public class Slimefunutils {
	private static int researchId = 9901;

	public static void registerItem(Category category, String name, ItemStack ItemStack, String ResearchName, int cost,
			RecipeType Recipetype, ItemStack[] RecipeStack, boolean registerResearch) {
		SlimefunItemStack items = new SlimefunItemStack(name, ItemStack);
		SlimefunItem item = new SlimefunItem(category, items, Recipetype, RecipeStack);
		item.register(ClayTech.getInstance());
		if (registerResearch) {
			researchId++;
			Slimefun.registerResearch(
					new Research(new NamespacedKey(ClayTech.getInstance(), name), researchId, ResearchName, cost),
					ItemStack);
		}
	}

	public static void registerArmors(Category category, String nameprefix, ItemStack[] ItemStack, String ResearchName,
			int cost, RecipeType Recipetype, ItemStack MaterialStack, boolean registerResearch) {

		SlimefunItemStack HELMET = new SlimefunItemStack(nameprefix + "_HELMET", ItemStack[0]);
		SlimefunItem HELMET_I = new SlimefunItem(category, HELMET, Recipetype, getArmorsStack(1, MaterialStack));
		HELMET_I.register(ClayTech.getInstance());
		SlimefunItemStack CHESTPLATE = new SlimefunItemStack(nameprefix + "_CHESTPLATE", ItemStack[1]);
		SlimefunItem CHESTPLATE_I = new SlimefunItem(category, CHESTPLATE, Recipetype,
				getArmorsStack(2, MaterialStack));
		CHESTPLATE_I.register(ClayTech.getInstance());
		SlimefunItemStack LEGGINGS = new SlimefunItemStack(nameprefix + "_LEGGINGS", ItemStack[2]);
		SlimefunItem LEGGINGS_I = new SlimefunItem(category, LEGGINGS, Recipetype, getArmorsStack(3, MaterialStack));
		LEGGINGS_I.register(ClayTech.getInstance());
		SlimefunItemStack BOOTS = new SlimefunItemStack(nameprefix + "_BOOTS", ItemStack[3]);
		SlimefunItem BOOTS_I = new SlimefunItem(category, BOOTS, Recipetype, getArmorsStack(4, MaterialStack));
		BOOTS_I.register(ClayTech.getInstance());
		if (registerResearch) {
			researchId++;
			Slimefun.registerResearch(new Research(new NamespacedKey(ClayTech.getInstance(), nameprefix + "_ARMORS"),
					researchId, ResearchName, cost), ItemStack);
		}
	}

	public static ItemStack[] getArmorsStack(int type, ItemStack Material) {
		if (type == 1) {
			return new ItemStack[] { Material, Material, Material, Material, null, Material, null, null, null };
		}
		if (type == 2) {
			return new ItemStack[] { Material, null, Material, Material, Material, Material, Material, Material,
					Material };
		}
		if (type == 3) {
			return new ItemStack[] { Material, Material, Material, Material, null, Material, Material, null, Material };
		}
		return new ItemStack[] { null, null, null, Material, null, Material, Material, null, Material };
	}
	
	public static void registerResource(GEOResource res) {
		SlimefunPlugin.getGPSNetwork().getResourceManager().register(res);
		SlimefunPlugin.getRegistry().getGEOResources().add(res);
	}
}
