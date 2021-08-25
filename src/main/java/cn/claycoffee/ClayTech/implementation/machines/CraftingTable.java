package cn.claycoffee.ClayTech.implementation.machines;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.ClayTechMachineRecipes;
import cn.claycoffee.ClayTech.api.events.PlayerCraftItemEvent;
import cn.claycoffee.ClayTech.implementation.abstractMachines.ACraftingTable;
import cn.claycoffee.ClayTech.utils.Lang;
import io.github.thebusybiscuit.slimefun4.core.categories.LockedCategory;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class CraftingTable extends ACraftingTable {
    private static Map<Block, ItemStack[]> inputItem = new HashMap<>();
    private static Map<Block, ItemStack> outputItem = new HashMap<>();

    public CraftingTable(LockedCategory category, SlimefunItemStack item, String id, RecipeType recipeType,
                         ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public String getInventoryTitle() {
        return Lang.readMachinesText("CLAY_FUSION_MACHINE");
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.CRAFTING_TABLE);
    }

    @Override
    public int getEnergyConsumption() {
        return 16;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public String getMachineIdentifier() {
        return "CLAY_CRAFTING_TABLE";
    }

    @Override
    public int getCapacity() {
        return 128;
    }

    @Override
    public void registerDefaultRecipes() {
        this.registerRecipe(20, ClayTechMachineRecipes.BLIND_CORE, new ItemStack[]{ClayTechItems.BLIND_CORE});
        this.registerRecipe(100, ClayTechMachineRecipes.BLIND_SWORD, new ItemStack[]{ClayTechItems.BLIND_SWORD});
        this.registerRecipe(20, ClayTechMachineRecipes.POISON_EYE, new ItemStack[]{ClayTechItems.POISON_EYE});

        this.registerRecipe(20, ClayTechMachineRecipes.POISON_CORE, new ItemStack[]{ClayTechItems.POISON_CORE});

        this.registerRecipe(40, ClayTechMachineRecipes.ADVANCED_POISON_CORE,
                new ItemStack[]{ClayTechItems.ADVANCED_POISON_CORE});
        this.registerRecipe(20, ClayTechMachineRecipes.CONFUSION_CORE,
                new ItemStack[]{ClayTechItems.CONFUSION_CORE});
        this.registerRecipe(40, ClayTechMachineRecipes.ADVANCED_CONFUSION_CORE,
                new ItemStack[]{ClayTechItems.ADVANCED_CONFUSION_CORE});
        this.registerRecipe(20, ClayTechMachineRecipes.BLACK_ROCK_BLOCK,
                new ItemStack[]{ClayTechItems.BLACK_ROCK_BLOCK});
        this.registerRecipe(20, ClayTechMachineRecipes.SLOWNESS_CORE, new ItemStack[]{ClayTechItems.SLOWNESS_CORE});
        this.registerRecipe(40, ClayTechMachineRecipes.ADVANCED_SLOWNESS_CORE,
                new ItemStack[]{ClayTechItems.ADVANCED_SLOWNESS_CORE});
        this.registerRecipe(40, ClayTechMachineRecipes.ADVANCED_BLIND_CORE,
                new ItemStack[]{ClayTechItems.ADVANCED_BLIND_CORE});
        this.registerRecipe(400, ClayTechMachineRecipes.FOUR_BOW, new ItemStack[]{ClayTechItems.FOUR_BOW});
        this.registerRecipe(100, ClayTechMachineRecipes.POISON_SWORD, new ItemStack[]{ClayTechItems.POISON_SWORD});
        this.registerRecipe(100, ClayTechMachineRecipes.ANTI_SLOWNESS_BOOTS,
                new ItemStack[]{ClayTechItems.ANTI_SLOWNESS_BOOTS});
        this.registerRecipe(80, ClayTechMachineRecipes.BLISTERING_CORE,
                new ItemStack[]{ClayTechItems.BLISTERING_CORE});
        this.registerRecipe(30, ClayTechMachineRecipes.ELEMENT_UNIT, new ItemStack[]{ClayTechItems.ELEMENT_UNIT});
        this.registerRecipe(8, ClayTechMachineRecipes.HIGHSPEED_RAILWAY,
                new ItemStack[]{ClayTechItems.HIGHSPEED_RAILWAY});
        ItemStack elem8 = ClayTechItems.ELECTRIC_MOTOR_8.clone();
        elem8.setAmount(8);
        this.registerRecipe(8, ClayTechMachineRecipes.ELECTRIC_MOTOR_8, new ItemStack[]{elem8});

        this.registerRecipe(180, ClayTechMachineRecipes.REINFORCED_ALLOY_PICKAXE,
                new ItemStack[]{ClayTechItems.REINFORCED_ALLOY_PICKAXE});
        this.registerRecipe(40, ClayTechMachineRecipes.CLAY_FUSION_INGOT,
                new ItemStack[]{ClayTechItems.CLAY_FUSION_INGOT});
        this.registerRecipe(50, ClayTechMachineRecipes.CLAY_ALLOY_INGOT,
                new ItemStack[]{ClayTechItems.CLAY_ALLOY_INGOT});
        this.registerRecipe(300, ClayTechMachineRecipes.CLAY_ALLOY_PICKAXE,
                new ItemStack[]{ClayTechItems.CLAY_ALLOY_PICKAXE});
        this.registerRecipe(300, ClayTechMachineRecipes.CLAY_ALLOY_HELMET,
                new ItemStack[]{ClayTechItems.CLAY_ALLOY_HELMET});
        this.registerRecipe(300, ClayTechMachineRecipes.CLAY_ALLOY_CHESTPLATE,
                new ItemStack[]{ClayTechItems.CLAY_ALLOY_CHESTPLATE});
        this.registerRecipe(300, ClayTechMachineRecipes.CLAY_ALLOY_LEGGINGS,
                new ItemStack[]{ClayTechItems.CLAY_ALLOY_LEGGINGS});
        this.registerRecipe(300, ClayTechMachineRecipes.CLAY_ALLOY_BOOTS,
                new ItemStack[]{ClayTechItems.CLAY_ALLOY_BOOTS});

        this.registerRecipe(30, ClayTechMachineRecipes.MOTOR_CORE, new ItemStack[]{ClayTechItems.MOTOR_CORE});
        this.registerRecipe(60, ClayTechMachineRecipes.TEMPERATURE_RESISTANCE_OBSIDIAN,
                new ItemStack[]{ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN});
        this.registerRecipe(120, ClayTechMachineRecipes.ROCKET_ENGINE_SHELL,
                new ItemStack[]{ClayTechItems.ROCKET_ENGINE_SHELL});
        this.registerRecipe(45, ClayTechMachineRecipes.FUEL_TANK, new ItemStack[]{ClayTechItems.FUEL_TANK});
        this.registerRecipe(180, ClayTechMachineRecipes.ROCKET_ENGINE, new ItemStack[]{ClayTechItems.ROCKET_ENGINE});
        this.registerRecipe(120, ClayTechMachineRecipes.ROCKET_ANTENNA,
                new ItemStack[]{ClayTechItems.ROCKET_ANTENNA});
        this.registerRecipe(300, ClayTechMachineRecipes.ROCKET_CPU, new ItemStack[]{ClayTechItems.ROCKET_CPU});
        this.registerRecipe(400, ClayTechMachineRecipes.ROCKET_CONTROL_CORE,
                new ItemStack[]{ClayTechItems.ROCKET_CONTROL_CORE});
        this.registerRecipe(200, ClayTechMachineRecipes.ROCKET_FUEL_TANK,
                new ItemStack[]{ClayTechItems.ROCKET_FUEL_TANK});
        this.registerRecipe(45, ClayTechMachineRecipes.ROCKET_GLASS, new ItemStack[]{ClayTechItems.ROCKET_GLASS});
        this.registerRecipe(60, ClayTechMachineRecipes.ROCKET_STEEL_PLATE,
                new ItemStack[]{ClayTechItems.ROCKET_STEEL_PLATE});

        this.registerRecipe(50, ClayTechMachineRecipes.OXYGEN_TANK, new ItemStack[]{ClayTechItems.OXYGEN_TANK});
        this.registerRecipe(100, ClayTechMachineRecipes.SPACESUIT_OXYGEN_TANK,
                new ItemStack[]{ClayTechItems.SPACESUIT_OXYGEN_TANK});
        this.registerRecipe(300, ClayTechMachineRecipes.SPACESUIT_HELMET,
                new ItemStack[]{ClayTechItems.SPACESUIT_HELMET});
        this.registerRecipe(300, ClayTechMachineRecipes.SPACESUIT_CHESTPLATE,
                new ItemStack[]{ClayTechItems.SPACESUIT_CHESTPLATE});
        this.registerRecipe(300, ClayTechMachineRecipes.SPACESUIT_LEGGINGS,
                new ItemStack[]{ClayTechItems.SPACESUIT_LEGGINGS});
        this.registerRecipe(300, ClayTechMachineRecipes.SPACESUIT_BOOTS,
                new ItemStack[]{ClayTechItems.SPACESUIT_BOOTS});

        this.registerRecipe(60, ClayTechMachineRecipes.PLANET_BASE_SIGNER,
                new ItemStack[]{ClayTechItems.PLANET_BASE_SIGNER});
        this.registerRecipe(20, ClayTechMachineRecipes.TUBE, new ItemStack[]{ClayTechItems.TUBE});
        this.registerRecipe(60, ClayTechMachineRecipes.OXYGEN_DISTRIBUTER,
                new ItemStack[]{ClayTechItems.OXYGEN_DISTRIBUTER});

        this.registerRecipe(2, ClayTechMachineRecipes.COPPER_DUST_O, new ItemStack[]{SlimefunItems.COPPER_DUST});
        this.registerRecipe(2, ClayTechMachineRecipes.CLAY_FUSION_INGOT_O,
                new ItemStack[]{ClayTechItems.CLAY_FUSION_INGOT});

        this.registerRecipe(30, ClayTechMachineRecipes.INK_MODULE,
                new ItemStack[]{ClayTechItems.INK_MODULE});
        this.registerRecipe(60, ClayTechMachineRecipes.COPYING_MODULE,
                new ItemStack[]{ClayTechItems.COPYING_MODULE});
    }
}
