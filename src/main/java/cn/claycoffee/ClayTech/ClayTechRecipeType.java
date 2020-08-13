package cn.claycoffee.ClayTech;

import cn.claycoffee.ClayTech.utils.Lang;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class ClayTechRecipeType {
    public static final RecipeType CLAY_CRAFTING_TABLE = new RecipeType(
            new NamespacedKey(ClayTech.plugin, "CLAY_CRAFTIONG_TABLE"),
            new SlimefunItemStack("CLAY_CRAFTING_TABLE", ClayTechItems.CLAY_CRAFTING_TABLE), "",
            Lang.readMachineRecipesText("CLAY_FUSION_MACHINE"));
    public static final RecipeType CLAY_FOOD_CAULDRON = new RecipeType(
            new NamespacedKey(ClayTech.plugin, "CLAY_FOOD_CAULDRON"),
            new SlimefunItemStack("CLAY_FOOD_CAULDRON", ClayTechItems.CLAY_FOOD_CAULDRON), "",
            Lang.readMachineRecipesText("CLAY_ELETRIC_CAULDRON"));
    public static final RecipeType CLAY_FOOD_CHALKING_MACHINE = new RecipeType(
            new NamespacedKey(ClayTech.plugin, "CLAY_FOOD_CHALKING_MACHINE"),
            new SlimefunItemStack("CLAY_FOOD_CHALKING_MACHINE", ClayTechItems.CLAY_FOOD_CHALKING_MACHINE), "",
            Lang.readMachineRecipesText("CLAY_FOOD_CHALKING_MACHINE"));
    public static final RecipeType CLAY_ELEMENT_EXTRACTER = new RecipeType(
            new NamespacedKey(ClayTech.plugin, "CLAY_ELEMENT_EXTRACTER"),
            new SlimefunItemStack("CLAY_ELEMENT_EXTRACTER", ClayTechItems.CLAY_ELEMENT_EXTRACTER), "",
            Lang.readMachineRecipesText("CLAY_ELEMENT_EXTRACTER"));
    public static final RecipeType CLAY_EXPERIMENT_TABLE_BASIC = new RecipeType(
            new NamespacedKey(ClayTech.plugin, "CLAY_EXPERIMENT_TABLE_BASIC"),
            new SlimefunItemStack("CLAY_EXPERIMENT_TABLE_BASIC", ClayTechItems.CLAY_EXPERIMENT_TABLE_NORMAL), "",
            Lang.readMachineRecipesText("CLAY_EXPERIMENT_TABLE_BASIC"));
    public static final RecipeType CLAY_ROCKET_ASSEMBLING_MACHINE = new RecipeType(
            new NamespacedKey(ClayTech.plugin, "CLAY_ROCKET_ASSEMBLING_MACHINE"),
            new SlimefunItemStack("CLAY_ROCKET_ASSEMBLING_MACHINE", ClayTechItems.CLAY_ROCKET_ASSEMBLING_MACHINE), "",
            Lang.readMachineRecipesText("CLAY_ROCKET_ASSEMBLING_MACHINE"));
    public static final RecipeType CLAY_ROCKET_FUEL_GENERATOR = new RecipeType(
            new NamespacedKey(ClayTech.plugin, "CLAY_ROCKET_FUEL_GENERATOR"),
            new SlimefunItemStack("CLAY_ROCKET_FUEL_GENERATOR", ClayTechItems.CLAY_ROCKET_FUEL_GENERATOR), "",
            Lang.readMachineRecipesText("CLAY_ROCKET_FUEL_GENERATOR"));
    public static final RecipeType DIG_IN_THE_MOON = new RecipeType(
            new NamespacedKey(ClayTech.plugin, "DIG_IN_THE_MOON"), new SlimefunItemStack("DIG_IN_THE_MOON",
            new ItemStack(Material.IRON_PICKAXE), "", Lang.readMachineRecipesText("DIG_IN_THE_MOON")));
    public static final RecipeType DIG_IN_NON_EARTH = new RecipeType(
            new NamespacedKey(ClayTech.plugin, "DIG_IN_NON_EARTH"), new SlimefunItemStack("DIG_IN_NON_EARTH",
            new ItemStack(Material.IRON_PICKAXE), "", Lang.readMachineRecipesText("DIG_IN_NON_EARTH")));
    public static final RecipeType FISHING = new RecipeType(new NamespacedKey(ClayTech.plugin, "FISHING"),
            new SlimefunItemStack("FISHING", new ItemStack(Material.TROPICAL_FISH), "",
                    Lang.readMachineRecipesText("FISHING")));
    public static final RecipeType HARVEST = new RecipeType(new NamespacedKey(ClayTech.plugin, "HARVEST"),
            new SlimefunItemStack("HARVEST", new ItemStack(Material.DIAMOND_HOE), "",
                    Lang.readMachineRecipesText("HARVEST")));
    public static final RecipeType PLUCKING = new RecipeType(new NamespacedKey(ClayTech.plugin, "PLUCKING"),
            new SlimefunItemStack("PLUCKING", new ItemStack(Material.APPLE), "",
                    Lang.readMachineRecipesText("PLUCKING")));
    public static final RecipeType CLEANING = new RecipeType(new NamespacedKey(ClayTech.plugin, "CLEANING"),
            new SlimefunItemStack("CLEANING", new ItemStack(Material.WATER_BUCKET), "",
                    Lang.readMachineRecipesText("CLEANING")));
}
