package club.claycoffee.ClayTech.implementation.items;

import org.bukkit.NamespacedKey;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.ClayTechMachineRecipes;
import club.claycoffee.ClayTech.ClayTechRecipeType;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class Rockets {
	public Rockets() {
		// 平台
		Slimefunutils.registerItem(ClayTechItems.C_MACHINES, "ROCKET_LAUNCHER", ClayTechItems.ROCKET_LAUNCHER,
				"notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.ANTI_SLOWNESS_BOOTS,
				false);

		Research ms2 = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_UNIVERSE_MACHINE_2"), 9929,
				Lang.readResearchesText("CLAYTECH_UNIVERSE_MACHINE_II"), 55);
		ms2.addItems(SlimefunItem.getByItem(ClayTechItems.ROCKET_LAUNCHER));
		ms2.register();

		// 火箭一阶
		Slimefunutils.registerItem(ClayTechItems.C_OTHER, "ROCKET_1", ClayTechItems.ROCKET, "notresearch", 10,
				ClayTechRecipeType.CLAY_ROCKET_ASSEMBLING_MACHINE, ClayTechMachineRecipes.ROCKET_1, false);
	}
}
