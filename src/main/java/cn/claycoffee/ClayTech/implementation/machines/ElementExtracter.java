package cn.claycoffee.ClayTech.implementation.machines;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.api.events.PlayerExtractElementEvent;
import cn.claycoffee.ClayTech.implementation.abstractMachines.AExtracter;
import cn.claycoffee.ClayTech.utils.Lang;
import io.github.thebusybiscuit.slimefun4.core.categories.LockedCategory;
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

public class ElementExtracter extends AExtracter {
    public ElementExtracter(LockedCategory category, SlimefunItemStack item, String id, RecipeType recipeType,
                            ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    public String getInventoryTitle() {
        return Lang.readMachinesText("CLAY_ELEMENT_EXTRACTER");
    }

    public ItemStack getProgressBar() {
        return new ItemStack(Material.REDSTONE_TORCH);
    }

    public int getEnergyConsumption() {
        return 64;
    }

    public int getSpeed() {
        return 1;
    }

    @Override
    public int getCapacity() {
        return 1024;
    }

    public String getMachineIdentifier() {
        return "ELEMENT_EXTRACTER";
    }

    public void registerDefaultRecipes() {
        registerRecipe(10, new ItemStack[]{new ItemStack(Material.DIRT, 3)},
                new ItemStack[]{ClayTechItems.ELEMENT_OXYGEN});
        registerRecipe(10, new ItemStack[]{new ItemStack(Material.COAL, 8)},
                new ItemStack[]{ClayTechItems.ELEMENT_CARBON});
        registerRecipe(10, new ItemStack[]{new ItemStack(Material.SAND, 10)},
                new ItemStack[]{ClayTechItems.ELEMENT_SILICON});
    }
}
