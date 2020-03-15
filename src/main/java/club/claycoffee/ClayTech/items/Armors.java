package club.claycoffee.ClayTech.items;

import org.bukkit.NamespacedKey;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.Recipes;
import club.claycoffee.ClayTech.TRecipe;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class Armors {
	public Armors() {

		Slimefunutils.registerItem(ClayTechItems.C_ARMORS, "ANTI_SLOWNESS_BOOTS", ClayTechItems.ANTI_SLOWNESS_BOOTS, "notresearch",
				10, TRecipe.CLAY_CRAFTING_TABLE, Recipes.ANTI_SLOWNESS_BOOTS, false);

		Research basic = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_ARMORS"), 9908,
				Lang.readResearchesText("CLAYTECH_ARMORS_I"), 50);
		basic.addItems(SlimefunItem.getByItem(ClayTechItems.ANTI_SLOWNESS_BOOTS));
		basic.register();
	}
}
