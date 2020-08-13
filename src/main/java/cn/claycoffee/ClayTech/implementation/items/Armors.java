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
import org.bukkit.inventory.ItemStack;

public class Armors {
    public Armors() {

        Slimefunutils.registerItem(ClayTechItems.C_ARMORS, "ANTI_SLOWNESS_BOOTS", ClayTechItems.ANTI_SLOWNESS_BOOTS,
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.ANTI_SLOWNESS_BOOTS,
                false);

        Research basic = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_ARMORS"), 9908,
                Lang.readResearchesText("CLAYTECH_ARMORS_I"), 50);
        basic.addItems(SlimefunItem.getByItem(ClayTechItems.ANTI_SLOWNESS_BOOTS));
        basic.register();

        Slimefunutils.registerArmors(ClayTechItems.C_ARMORS, "CLAY_ALLOY",
                new ItemStack[]{ClayTechItems.CLAY_ALLOY_HELMET, ClayTechItems.CLAY_ALLOY_CHESTPLATE,
                        ClayTechItems.CLAY_ALLOY_LEGGINGS, ClayTechItems.CLAY_ALLOY_BOOTS},
                "notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechItems.CLAY_ALLOY_INGOT, false);
        Research clayalloy = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_CLAY_ALLOY_ARMORS_I"),
                9924, Lang.readResearchesText("CLAYTECH_CLAY_ALLOY_ARMORS_I"), 65);
        clayalloy.addItems(SlimefunItem.getByItem(ClayTechItems.CLAY_ALLOY_HELMET),
                SlimefunItem.getByItem(ClayTechItems.CLAY_ALLOY_CHESTPLATE),
                SlimefunItem.getByItem(ClayTechItems.CLAY_ALLOY_LEGGINGS),
                SlimefunItem.getByItem(ClayTechItems.CLAY_ALLOY_BOOTS));
        clayalloy.register();
    }
}
