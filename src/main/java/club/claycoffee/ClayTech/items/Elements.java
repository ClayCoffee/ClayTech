package club.claycoffee.ClayTech.items;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.Recipes;
import club.claycoffee.ClayTech.TRecipe;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class Elements {
	public Elements() {
		ItemStack[] unit = { SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT,
				SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, null,
				SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT,
				SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT };

		Slimefunutils.registerItem(Defines.C_ELEMENTS, "ELEMENT_UNIT", Defines.ELEMENT_UNIT, "notresearch", 10,
				TRecipe.CLAY_CRAFTING_TABLE, unit, false);
		Slimefunutils.registerItem(Defines.C_ELEMENTS, "ELEMENT_OXYGEN", Defines.ELEMENT_OXYGEN, "notresearch", 10,
				TRecipe.CLAY_ELEMENT_EXTRACTER, Recipes.ELEMENT_OXYGEN, false);
		Slimefunutils.registerItem(Defines.C_ELEMENTS, "ELEMENT_CARBON", Defines.ELEMENT_CARBON, "notresearch", 10,
				TRecipe.CLAY_ELEMENT_EXTRACTER, Recipes.ELEMENT_CARBON, false);
		Slimefunutils.registerItem(Defines.C_ELEMENTS, "ELEMENT_SILICON", Defines.ELEMENT_SILICON, "notresearch", 10,
				TRecipe.CLAY_ELEMENT_EXTRACTER, Recipes.ELEMENT_SILICON, false);
		

		Research before_element = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_BEFORE_ELEMENT"),
				9919, "元素 I", 50);

		before_element.addItems(SlimefunItem.getByItem(Defines.ELEMENT_UNIT),
				SlimefunItem.getByItem(Defines.ELEMENT_OXYGEN),SlimefunItem.getByItem(Defines.ELEMENT_CARBON),SlimefunItem.getByItem(Defines.ELEMENT_SILICON));
		before_element.register();
	}
}