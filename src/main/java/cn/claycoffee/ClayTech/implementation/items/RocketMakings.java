package cn.claycoffee.ClayTech.implementation.items;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.ClayTechMachineRecipes;
import cn.claycoffee.ClayTech.ClayTechRecipeType;
import cn.claycoffee.ClayTech.utils.Lang;
import cn.claycoffee.ClayTech.utils.Slimefunutils;
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.NamespacedKey;

public class RocketMakings {
    public RocketMakings() {
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "MOTOR_CORE", ClayTechItems.MOTOR_CORE, "notresearch", 10,
                ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.MOTOR_CORE, false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "TEMPERATURE_RESISTANCE_OBSIDIAN",
                ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN, "notresearch", 10,
                ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.TEMPERATURE_RESISTANCE_OBSIDIAN, false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ROCKET_ENGINE_SHELL", ClayTechItems.ROCKET_ENGINE_SHELL,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.ROCKET_ENGINE_SHELL,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "FUEL_TANK", ClayTechItems.FUEL_TANK, "notresearch", 10,
                ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.FUEL_TANK, false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ROCKET_ENGINE", ClayTechItems.ROCKET_ENGINE,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.ROCKET_ENGINE, false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ROCKET_ANTENNA", ClayTechItems.ROCKET_ANTENNA,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.ROCKET_ANTENNA,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ROCKET_CPU", ClayTechItems.ROCKET_CPU, "notresearch", 10,
                ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.ROCKET_CPU, false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ROCKET_CONTROL_CORE", ClayTechItems.ROCKET_CONTROL_CORE,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.ROCKET_CONTROL_CORE,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ROCKET_FUEL_TANK", ClayTechItems.ROCKET_FUEL_TANK,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.ROCKET_FUEL_TANK,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ROCKET_GLASS", ClayTechItems.ROCKET_GLASS, "notresearch",
                10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.ROCKET_GLASS, false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ROCKET_STEEL_PLATE", ClayTechItems.ROCKET_STEEL_PLATE,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.ROCKET_STEEL_PLATE,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "MIXED_ROCKET_FUEL", ClayTechItems.MIXED_ROCKET_FUEL,
                "notresearch", 10, ClayTechRecipeType.CLAY_ROCKET_FUEL_GENERATOR,
                ClayTechMachineRecipes.MIXED_ROCKET_FUEL, false);

        Research rk1 = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_ROCKETS_1"), 9926,
                Lang.readResearchesText("CLAYTECH_ROCKETS_I"), 85);
        rk1.addItems(SlimefunItem.getByItem(ClayTechItems.MOTOR_CORE),
                SlimefunItem.getByItem(ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN),
                SlimefunItem.getByItem(ClayTechItems.ROCKET_ENGINE_SHELL),
                SlimefunItem.getByItem(ClayTechItems.FUEL_TANK), SlimefunItem.getByItem(ClayTechItems.ROCKET_ENGINE),
                SlimefunItem.getByItem(ClayTechItems.ROCKET_ANTENNA), SlimefunItem.getByItem(ClayTechItems.ROCKET_CPU),
                SlimefunItem.getByItem(ClayTechItems.ROCKET_CONTROL_CORE),
                SlimefunItem.getByItem(ClayTechItems.ROCKET_FUEL_TANK),
                SlimefunItem.getByItem(ClayTechItems.ROCKET_GLASS),
                SlimefunItem.getByItem(ClayTechItems.ROCKET_STEEL_PLATE),
                SlimefunItem.getByItem(ClayTechItems.MIXED_ROCKET_FUEL));
        rk1.register();

        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "OXYGEN_TANK", ClayTechItems.OXYGEN_TANK, "notresearch",
                10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.OXYGEN_TANK, false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "SPACESUIT_OXYGEN_TANK",
                ClayTechItems.SPACESUIT_OXYGEN_TANK, "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE,
                ClayTechMachineRecipes.SPACESUIT_OXYGEN_TANK, false);

        Slimefunutils.registerItem(ClayTechItems.C_ARMORS, "SPACESUIT_HELMET", ClayTechItems.SPACESUIT_HELMET,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.SPACESUIT_HELMET,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_ARMORS, "SPACESUIT_CHESTPLATE", ClayTechItems.SPACESUIT_CHESTPLATE,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.SPACESUIT_CHESTPLATE,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_ARMORS, "SPACESUIT_LEGGINGS", ClayTechItems.SPACESUIT_LEGGINGS,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.SPACESUIT_LEGGINGS,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_ARMORS, "SPACESUIT_BOOTS", ClayTechItems.SPACESUIT_BOOTS,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.SPACESUIT_BOOTS,
                false);

        Research ss1 = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_SPACESUIT_1"), 9927,
                Lang.readResearchesText("CLAYTECH_SPACESUIT_I"), 55);
        ss1.addItems(SlimefunItem.getByItem(ClayTechItems.OXYGEN_TANK),
                SlimefunItem.getByItem(ClayTechItems.SPACESUIT_OXYGEN_TANK),
                SlimefunItem.getByItem(ClayTechItems.SPACESUIT_HELMET),
                SlimefunItem.getByItem(ClayTechItems.SPACESUIT_CHESTPLATE),
                SlimefunItem.getByItem(ClayTechItems.SPACESUIT_LEGGINGS),
                SlimefunItem.getByItem(ClayTechItems.SPACESUIT_BOOTS));
        ss1.register();

        Research ms1 = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_UNIVERSE_MACHINE_1"), 9928,
                Lang.readResearchesText("CLAYTECH_UNIVERSE_MACHINE_I"), 70);
        ms1.addItems(SlimefunItem.getByItem(ClayTechItems.CLAY_ROCKET_ASSEMBLING_MACHINE),
                SlimefunItem.getByItem(ClayTechItems.CLAY_ROCKET_FUEL_GENERATOR),
                SlimefunItem.getByItem(ClayTechItems.CLAY_ROCKET_FUEL_INJECTOR),
                SlimefunItem.getByItem(ClayTechItems.CLAY_SPACESUIT_OXYGEN_INJECTOR));
        ms1.register();

        Slimefunutils.registerItem(ClayTechItems.C_OTHER, "PLANET_BASE_SIGNER", ClayTechItems.PLANET_BASE_SIGNER,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.PLANET_BASE_SIGNER,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "TUBE", ClayTechItems.TUBE, "notresearch", 10,
                ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.TUBE, false);
        Slimefunutils.registerItem(ClayTechItems.C_OTHER, "OXYGEN_DISTRIBUTER", ClayTechItems.OXYGEN_DISTRIBUTER,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.OXYGEN_DISTRIBUTER,
                false);

        Research bs1 = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_BASE_1"), 9930,
                Lang.readResearchesText("CLAYTECH_BASE_I"), 50);
        bs1.addItems(SlimefunItem.getByItem(ClayTechItems.PLANET_BASE_SIGNER),
                SlimefunItem.getByItem(ClayTechItems.TUBE), SlimefunItem.getByItem(ClayTechItems.OXYGEN_DISTRIBUTER));
        bs1.register();

    }
}
