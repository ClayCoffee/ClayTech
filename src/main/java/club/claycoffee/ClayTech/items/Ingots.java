package club.claycoffee.ClayTech.items;

import org.bukkit.NamespacedKey;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.ClayTechMachineRecipes;
import club.claycoffee.ClayTech.ClayTechRecipeType;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class Ingots {
	public Ingots() {
		Slimefunutils.registerItem(ClayTechItems.C_ORESTHINGS, "CLAY_FUSION_INGOT", ClayTechItems.CLAY_FUSION_INGOT,
				"notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.CLAY_FUSION_INGOT, false);
		Slimefunutils.registerItem(ClayTechItems.C_ORESTHINGS, "CLAY_ALLOY_INGOT", ClayTechItems.CLAY_ALLOY_INGOT,
				"notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.CLAY_ALLOY_INGOT, false);

		Research rs = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_OREINGOTS_1"), 9923,
				Lang.readResearchesText("CLAYTECH_OREINGOTS_I"), 50);
		rs.addItems(SlimefunItem.getByItem(ClayTechItems.CLAY_FUSION_INGOT),
				SlimefunItem.getByItem(ClayTechItems.CLAY_ALLOY_INGOT));
		rs.register();
	}
}
