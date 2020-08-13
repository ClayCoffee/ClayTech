package cn.claycoffee.ClayTech.implementation.machines;

import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.ClayTechMachineRecipes;
import cn.claycoffee.ClayTech.implementation.abstractMachines.AExperimentTable;
import cn.claycoffee.ClayTech.utils.Lang;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ExperimentTableNormal extends AExperimentTable {

    public ExperimentTableNormal(Category category, SlimefunItemStack item, String id, RecipeType recipeType,
                                 ItemStack[] recipe) {
        super(category, item, id, recipeType, recipe);
    }

    @Override
    public String getInventoryTitle() {
        return Lang.readMachinesText("CLAY_EXPERIMENTTABLE_NORMAL");
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.TNT);
    }

    @Override
    public int getEnergyConsumption() {
        return 80;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public String getMachineIdentifier() {
        return null;
    }

    @Override
    public void registerDefaultRecipes() {
        this.registerRecipe(20, ClayTechMachineRecipes.SILICON_INGOT, new ItemStack[]{ClayTechItems.SILICON_INGOT});
    }
}
