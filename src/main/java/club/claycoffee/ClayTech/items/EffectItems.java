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

public class EffectItems {
	public EffectItems() {
		Slimefunutils.registerItem(ClayTechItems.C_WEAPONS, "TNT_EXPLOSION_CREATER", ClayTechItems.TNT_EXPLOSION_CREATER, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, Recipes.TNT_EXPLOSION_CREATER, false);


		Research rs = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_AFT_1"), 9921,
				Lang.readResearchesText("CLAYTECH_EFFECT_ITEM_I"), 30);
		rs.addItems(SlimefunItem.getByItem(ClayTechItems.TNT_EXPLOSION_CREATER));
		rs.register();
	}
}
