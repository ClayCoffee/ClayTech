package cn.claycoffee.ClayTech.implementation.abstractMachines;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.inventory.ItemStack;

public abstract class ANewContainer extends AContainer {
    public ANewContainer(Category category, SlimefunItemStack item, RecipeType recipeType,
                         ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }
}