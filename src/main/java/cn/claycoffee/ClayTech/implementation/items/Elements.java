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

public class Elements {
    public Elements() {

        Slimefunutils.registerItem(ClayTechItems.C_ELEMENTS, "ELEMENT_UNIT", ClayTechItems.ELEMENT_UNIT, "notresearch",
                10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.ELEMENT_UNIT, false);
        Slimefunutils.registerItem(ClayTechItems.C_ELEMENTS, "ELEMENT_OXYGEN", ClayTechItems.ELEMENT_OXYGEN,
                "notresearch", 10, ClayTechRecipeType.CLAY_ELEMENT_EXTRACTER, ClayTechMachineRecipes.ELEMENT_OXYGEN,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_ELEMENTS, "ELEMENT_CARBON", ClayTechItems.ELEMENT_CARBON,
                "notresearch", 10, ClayTechRecipeType.CLAY_ELEMENT_EXTRACTER, ClayTechMachineRecipes.ELEMENT_CARBON,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_ELEMENTS, "ELEMENT_SILICON", ClayTechItems.ELEMENT_SILICON,
                "notresearch", 10, ClayTechRecipeType.CLAY_ELEMENT_EXTRACTER, ClayTechMachineRecipes.ELEMENT_SILICON,
                false);

        Research before_element = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_BEFORE_ELEMENT"),
                9919, Lang.readResearchesText("CLAYTECH_ELEMENTS_I"), 50);

        before_element.addItems(SlimefunItem.getByItem(ClayTechItems.ELEMENT_UNIT),
                SlimefunItem.getByItem(ClayTechItems.ELEMENT_OXYGEN),
                SlimefunItem.getByItem(ClayTechItems.ELEMENT_CARBON),
                SlimefunItem.getByItem(ClayTechItems.ELEMENT_SILICON));
        before_element.register();
    }
}