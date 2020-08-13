package cn.claycoffee.ClayTech.implementation.items;

import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

public class ClayFuelResource {
    public ClayFuelResource() {
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "CLAY_FUEL", ClayTechItems.CLAY_FUEL, "notresearch", 10,
                RecipeType.GEO_MINER, ClayTechItems.NORECIPE, false);
    }
}
