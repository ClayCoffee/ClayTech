package club.claycoffee.ClayTech.items;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.Recipes;
import club.claycoffee.ClayTech.TRecipe;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

import org.bukkit.NamespacedKey;

public class Elements {
	public Elements() {

		Slimefunutils.registerItem(ClayTechItems.C_ELEMENTS, "ELEMENT_UNIT", ClayTechItems.ELEMENT_UNIT, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, Recipes.ELEMENT_UNIT, false);
		Slimefunutils.registerItem(ClayTechItems.C_ELEMENTS, "ELEMENT_OXYGEN", ClayTechItems.ELEMENT_OXYGEN, "notresearch", 10,
				TRecipe.CLAY_ELEMENT_EXTRACTER, Recipes.ELEMENT_OXYGEN, false);
		Slimefunutils.registerItem(ClayTechItems.C_ELEMENTS, "ELEMENT_CARBON", ClayTechItems.ELEMENT_CARBON, "notresearch", 10,
				TRecipe.CLAY_ELEMENT_EXTRACTER, Recipes.ELEMENT_CARBON, false);
		Slimefunutils.registerItem(ClayTechItems.C_ELEMENTS, "ELEMENT_SILICON", ClayTechItems.ELEMENT_SILICON, "notresearch", 10,
				TRecipe.CLAY_ELEMENT_EXTRACTER, Recipes.ELEMENT_SILICON, false);

		Research before_element = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_BEFORE_ELEMENT"), 9919,
				Lang.readResearchesText("CLAYTECH_ELEMENTS_I"), 50);

		before_element.addItems(SlimefunItem.getByItem(ClayTechItems.ELEMENT_UNIT),
				SlimefunItem.getByItem(ClayTechItems.ELEMENT_OXYGEN), SlimefunItem.getByItem(ClayTechItems.ELEMENT_CARBON),
				SlimefunItem.getByItem(ClayTechItems.ELEMENT_SILICON));
		before_element.register();
	}
}