package cn.claycoffee.ClayTech.implementation.machines;

import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.implementation.abstractMachines.ARocketTable;
import cn.claycoffee.ClayTech.utils.Lang;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RocketAssemblingMachine extends ARocketTable {

    public RocketAssemblingMachine(Category category, SlimefunItemStack item, String id, RecipeType recipeType,
                                   ItemStack[] recipe) {
        super(category, item, id, recipeType, recipe);
    }

    @Override
    public String getInventoryTitle() {
        return Lang.readMachinesText("CLAY_ROCKET_ASSEMBLING_MACHINE");
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.REDSTONE_TORCH);
    }

    @Override
    public int getEnergyConsumption() {
        return 128;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public String getMachineIdentifier() {
        return "CLAY_ROCKET_ASSEMBLING_MACHINE";
    }

    @Override
    public void registerDefaultRecipes() {
        ItemStack[] ROCKET_1 = {null, ClayTechItems.ROCKET_GLASS, ClayTechItems.ROCKET_FUEL_TANK,
                ClayTechItems.ROCKET_GLASS, ClayTechItems.ROCKET_STEEL_PLATE, ClayTechItems.ROCKET_CONTROL_CORE,
                ClayTechItems.ROCKET_STEEL_PLATE, ClayTechItems.ROCKET_STEEL_PLATE, ClayTechItems.ROCKET_ENGINE,
                ClayTechItems.ROCKET_STEEL_PLATE};
        this.registerRecipe(600, ROCKET_1, new ItemStack[]{ClayTechItems.ROCKET});
    }

    @Override
    public int getCapacity() {
        return 512;
    }

}
