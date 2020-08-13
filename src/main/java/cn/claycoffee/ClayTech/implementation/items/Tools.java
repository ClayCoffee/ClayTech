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

public class Tools {
    public Tools() {
        Slimefunutils.registerItem(ClayTechItems.C_TOOLS, "REINFORCED_ALLOY_PICKAXE",
                ClayTechItems.REINFORCED_ALLOY_PICKAXE, "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE,
                ClayTechMachineRecipes.REINFORCED_ALLOY_PICKAXE, false);
        Slimefunutils.registerItem(ClayTechItems.C_TOOLS, "CLAY_ALLOY_PICKAXE", ClayTechItems.CLAY_ALLOY_PICKAXE,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.CLAY_ALLOY_PICKAXE,
                false);

        Research rs = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_TOOLS_1"), 9922,
                Lang.readResearchesText("CLAYTECH_TOOLS_I"), 35);
        rs.addItems(SlimefunItem.getByItem(ClayTechItems.REINFORCED_ALLOY_PICKAXE),
                SlimefunItem.getByItem(ClayTechItems.CLAY_ALLOY_PICKAXE));
        rs.register();
    }
}
