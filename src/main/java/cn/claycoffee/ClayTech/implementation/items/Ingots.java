package cn.claycoffee.ClayTech.implementation.items;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.ClayTechMachineRecipes;
import cn.claycoffee.ClayTech.ClayTechRecipeType;
import cn.claycoffee.ClayTech.utils.Lang;
import cn.claycoffee.ClayTech.utils.Slimefunutils;
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.NamespacedKey;

public class Ingots {
    public Ingots() {
        Slimefunutils.registerItem(ClayTechItems.C_ORESTHINGS, "CLAY_FUSION_INGOT", ClayTechItems.CLAY_FUSION_INGOT,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.CLAY_FUSION_INGOT,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_ORESTHINGS, "CLAY_ALLOY_INGOT", ClayTechItems.CLAY_ALLOY_INGOT,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.CLAY_ALLOY_INGOT,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_ORESTHINGS, "CLAY_SILICON_INGOT", ClayTechItems.SILICON_INGOT,
                "notresearch", 10, ClayTechRecipeType.CLAY_EXPERIMENT_TABLE_BASIC, ClayTechMachineRecipes.SILICON_INGOT,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_ORESTHINGS, "KREEP_ROCK", ClayTechItems.KREEP_ROCK, "notresearch",
                10, ClayTechRecipeType.DIG_IN_THE_MOON, ClayTechItems.NORECIPE, false);
        Slimefunutils.registerItem(ClayTechItems.C_ORESTHINGS, "KREEP_INGOT", ClayTechItems.KREEP_INGOT, "notresearch",
                10, RecipeType.SMELTERY, ClayTechMachineRecipes.KREEP_INGOT, false);
        Slimefunutils.registerItem(ClayTechItems.C_ORESTHINGS, "COPPER_ORE", ClayTechItems.COPPER_ORE, "notresearch",
                10, ClayTechRecipeType.DIG_IN_NON_EARTH, ClayTechItems.NORECIPE, false);
        Slimefunutils.registerItem(ClayTechItems.C_ORESTHINGS, "CLAY_FUSION_ORE", ClayTechItems.CLAY_FUSION_ORE,
                "notresearch", 10, ClayTechRecipeType.DIG_IN_NON_EARTH, ClayTechItems.NORECIPE, false);

        Research rs = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_OREINGOTS_1"), 9923,
                Lang.readResearchesText("CLAYTECH_OREINGOTS_I"), 50);
        rs.addItems(SlimefunItem.getByItem(ClayTechItems.CLAY_FUSION_INGOT),
                SlimefunItem.getByItem(ClayTechItems.CLAY_ALLOY_INGOT),
                SlimefunItem.getByItem(ClayTechItems.SILICON_INGOT));
        rs.register();

        Research rs2 = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_OREINGOTS_2"), 9925,
                Lang.readResearchesText("CLAYTECH_OREINGOTS_II"), 50);
        rs2.addItems(SlimefunItem.getByItem(ClayTechItems.KREEP_INGOT),
                SlimefunItem.getByItem(ClayTechItems.KREEP_ROCK), SlimefunItem.getByItem(ClayTechItems.COPPER_ORE),
                SlimefunItem.getByItem(ClayTechItems.CLAY_FUSION_ORE));
        rs2.register();
    }
}
