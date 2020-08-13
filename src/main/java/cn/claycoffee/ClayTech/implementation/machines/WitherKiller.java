package cn.claycoffee.ClayTech.implementation.machines;

import cn.claycoffee.ClayTech.implementation.abstractMachines.ACraftingTable;
import cn.claycoffee.ClayTech.utils.Lang;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * @Project: ClayTech
 * @Author: ClayCoffee
 * @Date: 2020/8/3 20:36
 * @Email: 1020757140@qq.com
 * AGPL 3.0
 */

public class WitherKiller extends ACraftingTable {
    public WitherKiller(Category category, SlimefunItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, id, recipeType, recipe);
    }

    @Override
    public String getInventoryTitle() {
        return Lang.readMachinesText("CLAY_WITHER_KILLER");
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.WITHER_SKELETON_SKULL);
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
        return "CLAY_WITHER_KILLER";
    }

    @Override
    public int getCapacity() {
        return 5120;
    }

    @Override
    public void registerDefaultRecipes() {
        ItemStack SOUL_SAND = new ItemStack(Material.SOUL_SAND);
        ItemStack WITHER_SKELETON_SKULL = new ItemStack(Material.WITHER_SKELETON_SKULL);
        this.registerRecipe(40, new ItemStack[]{WITHER_SKELETON_SKULL, WITHER_SKELETON_SKULL, WITHER_SKELETON_SKULL, SOUL_SAND, SOUL_SAND, SOUL_SAND, null, SOUL_SAND, null}, new ItemStack[]{new ItemStack(Material.NETHER_STAR)});
    }
}
