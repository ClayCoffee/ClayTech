package cn.claycoffee.ClayTech.implementation.items;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.utils.Lang;
import cn.claycoffee.ClayTech.utils.Slimefunutils;
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

/**
 * @Project: ClayTech
 * @Author: ClayCoffee
 * @Date: 2020/8/23 15:38
 * @Email: 1020757140@qq.com
 * AGPL 3.0
 */

public class Spacethings {
    public Spacethings() {
        ItemStack[] clayairlockplate = new ItemStack[]{new ItemStack(Material.STICKY_PISTON), new ItemStack(Material.STONE_PRESSURE_PLATE), new ItemStack(Material.STICKY_PISTON), new ItemStack(Material.STICKY_PISTON), SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.STICKY_PISTON), ClayTechItems.BLISTERING_CORE, ClayTechItems.BLISTERING_CORE, ClayTechItems.BLISTERING_CORE};
        ItemStack[] clayairlockblock = new ItemStack[]{SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT, null, SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.LEAD_INGOT};

        Slimefunutils.registerItem(ClayTechItems.C_OTHER, "CLAY_AIR_LOCK_PLATE",
                ClayTechItems.CLAY_AIR_LOCK_PLATE, "notresearch", 10, RecipeType.ENHANCED_CRAFTING_TABLE,
                clayairlockplate, false);
        Slimefunutils.registerItem(ClayTechItems.C_OTHER, "CLAY_AIR_LOCK_BLOCK", ClayTechItems.CLAY_AIR_LOCK_BLOCK,
                "notresearch", 10, RecipeType.ENHANCED_CRAFTING_TABLE, clayairlockblock,
                false);

        Research rs = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_SPACETHINGS_1"), 9936,
                Lang.readResearchesText("CLAYTECH_SPACETHINGS_I"), 70);
        rs.addItems(SlimefunItem.getByItem(ClayTechItems.CLAY_AIR_LOCK_BLOCK),
                SlimefunItem.getByItem(ClayTechItems.CLAY_AIR_LOCK_PLATE));
        rs.register();
    }
}
