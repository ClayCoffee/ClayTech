package cn.claycoffee.ClayTech.implementation.items;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.utils.Lang;
import cn.claycoffee.ClayTech.utils.Slimefunutils;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class Skulls {
    public Skulls() {
        // 合成方式
        ItemStack[] ClayCoffeeHeadRecipe = {new ItemStack(Material.DIAMOND_BLOCK), new ItemStack(Material.GOLD_BLOCK),
                new ItemStack(Material.DIAMOND_BLOCK), ClayTechItems.ARTIFICIAL_GOLD_INGOT, ClayTechItems.MAGIC_CLAY,
                ClayTechItems.ARTIFICIAL_GOLD_INGOT, new ItemStack(Material.DIAMOND_BLOCK),
                new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.DIAMOND_BLOCK)};
        ItemStack[] OtherHeadRecipe = {new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.GOLD_BLOCK),
                new ItemStack(Material.IRON_BLOCK), ClayTechItems.ARTIFICIAL_GOLD_NUGGET, ClayTechItems.MAGIC_CLAY,
                ClayTechItems.ARTIFICIAL_GOLD_NUGGET, new ItemStack(Material.IRON_BLOCK),
                new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.IRON_BLOCK)};
        ItemStack[] OtherHeadRecipe2 = {new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.GOLD_BLOCK),
                new ItemStack(Material.IRON_BLOCK), ClayTechItems.ARTIFICIAL_GOLD_NUGGET, ClayTechItems.CLAY_STICK,
                ClayTechItems.ARTIFICIAL_GOLD_NUGGET, new ItemStack(Material.IRON_BLOCK),
                new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.IRON_BLOCK)};
        ItemStack[] ClockRecipe = SlimefunPlugin.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_14)
                ? new ItemStack[]{new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT),
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT),
                ClayTechItems.MAGIC_CLAY, new ItemStack(Material.IRON_INGOT),
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.BLACK_DYE),
                new ItemStack(Material.IRON_INGOT)}
                : new ItemStack[]{new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT),
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT),
                ClayTechItems.MAGIC_CLAY, new ItemStack(Material.IRON_INGOT),
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.COAL),
                new ItemStack(Material.IRON_INGOT)};
        ItemStack[] LanternRecipe = {new ItemStack(Material.OAK_LOG), new ItemStack(Material.OAK_LOG),
                new ItemStack(Material.OAK_LOG), new ItemStack(Material.OAK_LOG), new ItemStack(Material.GLOWSTONE),
                new ItemStack(Material.OAK_LOG), new ItemStack(Material.OAK_LOG), ClayTechItems.MAGIC_CLAY,
                new ItemStack(Material.OAK_LOG)};

        // 注册物品
        Slimefunutils.registerItem(ClayTechItems.C_DECORATES, "CLAYCOFFEE_HEAD", ClayTechItems.CLAYCOFFEE_HEAD,
                "notresearch", 10, RecipeType.ANCIENT_ALTAR, ClayCoffeeHeadRecipe, false);
        Slimefunutils.registerItem(ClayTechItems.C_DECORATES, "STALIN_HEAD", ClayTechItems.STALIN_HEAD, "notresearch",
                10, RecipeType.ANCIENT_ALTAR, OtherHeadRecipe, false);
        Slimefunutils.registerItem(ClayTechItems.C_DECORATES, "MARX_HEAD", ClayTechItems.MARX_HEAD, "notresearch", 10,
                RecipeType.ANCIENT_ALTAR, OtherHeadRecipe2, false);
        Slimefunutils.registerItem(ClayTechItems.C_DECORATES, "CLOCK_C", ClayTechItems.CLOCK_C, "notresearch", 10,
                RecipeType.ENHANCED_CRAFTING_TABLE, ClockRecipe, false);
        Slimefunutils.registerItem(ClayTechItems.C_DECORATES, "LANTERN_C", ClayTechItems.LANTERN_C, "notresearch", 10,
                RecipeType.ENHANCED_CRAFTING_TABLE, LanternRecipe, false);

        // 注册研究
        Research skull_basic = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_SKULL_BASIC"), 9907,
                Lang.readResearchesText("CLAYTECH_SKULL_I"), 50);
        skull_basic.addItems(SlimefunItem.getByItem(ClayTechItems.CLAYCOFFEE_HEAD),
                SlimefunItem.getByItem(ClayTechItems.STALIN_HEAD), SlimefunItem.getByItem(ClayTechItems.MARX_HEAD));
        skull_basic.register();

        Research skull_basic2 = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_SKULL_BASIC"), 9914,
                Lang.readResearchesText("CLAYTECH_DECORATES_I"), 50);
        skull_basic2.addItems(SlimefunItem.getByItem(ClayTechItems.CLOCK_C),
                SlimefunItem.getByItem(ClayTechItems.LANTERN_C));
        skull_basic2.register();
    }
}
