package cn.claycoffee.ClayTech.implementation.items;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.utils.Lang;
import cn.claycoffee.ClayTech.utils.Slimefunutils;
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class Golden_things {
    public Golden_things() {
        ItemStack[] recipec = {new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.GOLD_INGOT),
                new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.GOLD_INGOT), ClayTechItems.MAGIC_CLAY,
                new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.GOLD_INGOT),
                new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.GOLD_INGOT)};
        ItemStack[] reciped = {ClayTechItems.ARTIFICIAL_GOLD_NUGGET, ClayTechItems.ARTIFICIAL_GOLD_NUGGET,
                ClayTechItems.ARTIFICIAL_GOLD_NUGGET, ClayTechItems.ARTIFICIAL_GOLD_NUGGET, ClayTechItems.MAGIC_CLAY,
                ClayTechItems.ARTIFICIAL_GOLD_NUGGET, ClayTechItems.ARTIFICIAL_GOLD_NUGGET,
                ClayTechItems.ARTIFICIAL_GOLD_NUGGET, ClayTechItems.ARTIFICIAL_GOLD_NUGGET};
        ItemStack[] recipee = {null, ClayTechItems.MAGIC_CLAY, null, null, ClayTechItems.ARTIFICIAL_GOLD_INGOT_O, null,
                null, null, null};
        ItemStack[] recipef = {ClayTechItems.ARTIFICIAL_GOLD_INGOT, ClayTechItems.ARTIFICIAL_GOLD_INGOT,
                ClayTechItems.ARTIFICIAL_GOLD_INGOT, ClayTechItems.ARTIFICIAL_GOLD_INGOT,
                ClayTechItems.ARTIFICIAL_GOLD_INGOT, ClayTechItems.ARTIFICIAL_GOLD_INGOT,
                ClayTechItems.ARTIFICIAL_GOLD_INGOT, ClayTechItems.ARTIFICIAL_GOLD_INGOT,
                ClayTechItems.ARTIFICIAL_GOLD_INGOT};
        ItemStack[] recipeg = {ClayTechItems.ARTIFICIAL_GOLD_BLOCK, ClayTechItems.ARTIFICIAL_GOLD_BLOCK,
                ClayTechItems.ARTIFICIAL_GOLD_BLOCK, ClayTechItems.ARTIFICIAL_GOLD_BLOCK,
                new ItemStack(Material.GOLDEN_APPLE), ClayTechItems.ARTIFICIAL_GOLD_BLOCK,
                ClayTechItems.ARTIFICIAL_GOLD_BLOCK, ClayTechItems.ARTIFICIAL_GOLD_BLOCK,
                ClayTechItems.ARTIFICIAL_GOLD_BLOCK};

        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ARTIFICIAL_GOLD_NUGGET",
                ClayTechItems.ARTIFICIAL_GOLD_NUGGET, "notresearch", 10, RecipeType.ENHANCED_CRAFTING_TABLE, recipec,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ARTIFICIAL_GOLD_INGOT_O",
                ClayTechItems.ARTIFICIAL_GOLD_INGOT_O, "notresearch", 10, RecipeType.ENHANCED_CRAFTING_TABLE, reciped,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ARTIFICIAL_GOLD_INGOT",
                ClayTechItems.ARTIFICIAL_GOLD_INGOT, "notresearch", 10, RecipeType.ENHANCED_CRAFTING_TABLE, recipee,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_MATERIALS, "ARTIFICIAL_GOLD_BLOCK",
                ClayTechItems.ARTIFICIAL_GOLD_BLOCK, "notresearch", 10, RecipeType.ENHANCED_CRAFTING_TABLE, recipef,
                false);
        Slimefunutils.registerItem(ClayTechItems.C_FOOD, "ARTIFICIAL_ENCHANTED_GOLDEN_APPLE",
                ClayTechItems.ARTIFICIAL_ENCHANTED_GOLDEN_APPLE, "notresearch", 10, RecipeType.ANCIENT_ALTAR, recipeg,
                false);

        Research artificial_basic = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_ARTIFICIAL_BASIC"),
                9901, Lang.readResearchesText("CLAYTECH_ARTIFICIAL_I"), 45);
        artificial_basic.addItems(SlimefunItem.getByItem(ClayTechItems.ARTIFICIAL_GOLD_NUGGET),
                SlimefunItem.getByItem(ClayTechItems.ARTIFICIAL_GOLD_INGOT),
                SlimefunItem.getByItem(ClayTechItems.ARTIFICIAL_GOLD_BLOCK));
        artificial_basic.register();

        Research artificial_basic_o = new Research(
                new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_ARTIFICIAL_BASIC_O"), 9902,
                Lang.readResearchesText("CLAYTECH_ARTIFICIAL_ORE_I"), 30);
        artificial_basic_o.addItems(SlimefunItem.getByItem(ClayTechItems.ARTIFICIAL_GOLD_INGOT_O));
        artificial_basic_o.register();

        Research artificial_enchanted_golden_apple_r = new Research(
                new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_ENCHANTED_GOLDEN_APPLE"), 9903,
                Lang.readResearchesText("CLAYTECH_ARTIFICIAL_ENCHANTED_GOLDEN_APPLE"), 100);
        artificial_enchanted_golden_apple_r
                .addItems(SlimefunItem.getByItem(ClayTechItems.ARTIFICIAL_ENCHANTED_GOLDEN_APPLE));
        artificial_enchanted_golden_apple_r.register();
    }
}
