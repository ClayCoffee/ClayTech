package club.claycoffee.ClayTech.implementation.items;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechData;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.ClayTechMachineRecipes;
import club.claycoffee.ClayTech.ClayTechRecipeType;
import club.claycoffee.ClayTech.api.Planet;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.PlanetUtils;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import club.claycoffee.ClayTech.utils.StrUtils;
import club.claycoffee.ClayTech.utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockPlaceHandler;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockUseHandler;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.Slimefun;

public class Rockets {
	private static final int[] BORDER = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 17, 18, 26, 27, 35, 36, 44, 45, 47, 48, 49,
			50, 51, 53 };
	private static final int[] BORDER_2 = { 10, 11, 12, 14, 15, 16 };
	private static final ItemStack BORDER_ITEM = Utils.newItemD(Material.LIGHT_BLUE_STAINED_GLASS_PANE,
			Lang.readMachinesText("SPLIT_LINE"));
	private static final ItemStack OTHERBORDER_ITEM = Utils.newItemD(Material.LIME_STAINED_GLASS_PANE,
			Lang.readMachinesText("SPLIT_LINE"));
	private static int currentPage = 1;

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

				}, new BlockUseHandler() {

					@Override
					public void onRightClick(PlayerRightClickEvent ev) {
						PlayerInteractEvent e = ev.getInteractEvent();
						if (e.hasBlock() && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
							Block b = e.getClickedBlock();
							if (BlockStorage.checkID(b) != null) {
								if (BlockStorage.checkID(b).equalsIgnoreCase("ROCKET_LAUNCHER")) {
									if (e.hasItem()) {
										if (!Slimefun.hasUnlocked(e.getPlayer(), e.getItem(), true)) {
											return;
										}
									}
									if (!Slimefun.hasUnlocked(e.getPlayer(), ClayTechItems.ROCKET_LAUNCHER, true)) {
										return;
									}
									Map<String, String> jbj = StrUtils.parseJSON(BlockStorage.getBlockInfoAsJson(b));
									String ownerName = jbj.get("owner");
									if (ownerName.equalsIgnoreCase(e.getPlayer().getName())) {
										Planet current = PlanetUtils.getPlanet(b.getWorld());
										if (current == null) {
											e.getPlayer().sendMessage(Lang.readGeneralText("NotAtAPlanet"));
											return;
										}
										if (Utils.getMetadata(b, "currentPage") != null) {
											currentPage = new Integer(Utils.getMetadata(b, "currentPage")).intValue();
										}
										Inventory Preset = Bukkit.createInventory(null, 54,
												Lang.readMachinesText("ROCKET_LAUNCHER"));
										if (!ClayTechData.RunningLaunchersG.containsKey(Preset)) {
											ClayTechData.RunningLaunchersG.put(Preset, b);
										}
										Preset.setItem(5, BORDER_ITEM);
										for (int eachID : BORDER) {
											Preset.setItem(eachID, BORDER_ITEM);
										}
										for (int eachID : BORDER_2) {
											Preset.setItem(eachID, OTHERBORDER_ITEM);
										}
										Preset.setItem(5, BORDER_ITEM);

										Preset = PlanetUtils.renderLauncherMenu(current, Preset, currentPage);

										e.getPlayer().openInventory(Preset);
									} else {
										e.getPlayer().sendMessage(Lang.readGeneralText("notOwner"));
										e.setCancelled(true);
										return;
									}
								}
							}
						}
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
