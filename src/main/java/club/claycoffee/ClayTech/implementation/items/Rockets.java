package club.claycoffee.ClayTech.implementation.items;

import org.bukkit.NamespacedKey;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.ClayTechMachineRecipes;
import club.claycoffee.ClayTech.ClayTechRecipeType;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockPlaceHandler;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public class Rockets {
	public Rockets() {
		// 平台
		ItemStack[] rocketlauncher = { ClayTechItems.CLAY_FUSION_INGOT, ClayTechItems.CLAY_FUSION_INGOT,
				ClayTechItems.CLAY_FUSION_INGOT, ClayTechItems.CLAY_FUSION_INGOT, SlimefunItems.PROGRAMMABLE_ANDROID,
				ClayTechItems.CLAY_FUSION_INGOT, ClayTechItems.CLAY_FUSION_INGOT, ClayTechItems.CLAY_FUSION_INGOT,
				ClayTechItems.CLAY_FUSION_INGOT };

		Slimefunutils.registerItem(ClayTechItems.C_MACHINES, "ROCKET_LAUNCHER", ClayTechItems.ROCKET_LAUNCHER,
				"notresearch", 10, RecipeType.ENHANCED_CRAFTING_TABLE, rocketlauncher, false,
				new ItemHandler[] { new BlockPlaceHandler() {

					@Override
					public boolean onBlockPlace(BlockPlaceEvent e, ItemStack item) {
						BlockStorage.addBlockInfo(e.getBlockPlaced(), "owner", e.getPlayer().getName(), true);
						return true;
					}

				} });

		Research ms2 = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_UNIVERSE_MACHINE_2"), 9929,
				Lang.readResearchesText("CLAYTECH_UNIVERSE_MACHINE_II"), 55);
		ms2.addItems(SlimefunItem.getByItem(ClayTechItems.ROCKET_LAUNCHER));
		ms2.register();

		// 火箭一阶
		Slimefunutils.registerItem(ClayTechItems.C_OTHER, "ROCKET_1", ClayTechItems.ROCKET, "notresearch", 10,
				ClayTechRecipeType.CLAY_ROCKET_ASSEMBLING_MACHINE, ClayTechMachineRecipes.ROCKET_1, false);
	}
}
