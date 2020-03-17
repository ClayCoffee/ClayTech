package club.claycoffee.ClayTech.Machines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.api.listeners.MachineTickEvent;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.InventoryBlock;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;

@SuppressWarnings("deprecation")
public abstract class ACraftingTable extends SlimefunItem implements InventoryBlock, EnergyNetComponent {
	public static Map<Block, MachineRecipe> processing = new HashMap<>();
	public static Map<Block, Integer> progress = new HashMap<>();
	public final static int[] inputslots = new int[] { 19, 20, 21, 28, 29, 30, 37, 38, 39 };
	public final static int[] outputslots = new int[] { 34 };

	protected final List<MachineRecipe> recipes = new ArrayList<>();

	private static final int[] BORDER = { 0, 1, 2, 3, 5, 6, 7, 8, 14, 15, 16, 17, 23, 41, 50, 51, 52, 53, 32 };
	private static final int[] BORDER_IN = { 9, 10, 11, 12, 13, 18, 22, 27, 31, 36, 40, 45, 46, 47, 48, 49 };
	private static final int[] BORDER_OUT = { 24, 25, 26, 33, 35, 42, 43, 44 };
	private static final ItemStack BORDER_ITEM = Utils.newItemD(Material.LIGHT_BLUE_STAINED_GLASS_PANE,
			Lang.readMachinesText("SPLIT_LINE"));
	private static final ItemStack OTHERBORDER_ITEM = Utils.newItemD(Material.LIME_STAINED_GLASS_PANE,
			Lang.readMachinesText("SPLIT_LINE"));
	SlimefunItemStack items;

	public ACraftingTable(Category category, SlimefunItemStack item, String id, RecipeType recipeType,
			ItemStack[] recipe) {

		super(category, item, recipeType, recipe);

		createPreset(this, getInventoryTitle(), this::SetupMenu);

		registerBlockHandler(id, (p, b, tool, reason) -> {
			BlockMenu inv = BlockStorage.getInventory(b);
			if (inv != null) {
				for (int slot : getInputSlots()) {
					if (inv.getItemInSlot(slot) != null) {
						b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
						inv.replaceExistingItem(slot, null);
					}
				}

				for (int slot : getOutputSlots()) {
					if (inv.getItemInSlot(slot) != null) {
						b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
						inv.replaceExistingItem(slot, null);
					}
				}
			}

			progress.remove(b);
			processing.remove(b);
			return true;
		});

		this.registerDefaultRecipes();
	}

	public int[] getInputSlots() {
		return inputslots;
	}

	@Override
	public int[] getOutputSlots() {
		return outputslots;
	}

	public abstract String getInventoryTitle();

	public abstract ItemStack getProgressBar();

	public abstract int getEnergyConsumption();

	public abstract int getSpeed();

	public abstract String getMachineIdentifier();

	public void SetupMenu(BlockMenuPreset Preset) {
		Preset.addItem(5, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
		for (int eachID : BORDER) {
			Preset.addItem(eachID, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
		}
		for (int eachID : BORDER_IN) {
			Preset.addItem(eachID, OTHERBORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
		}
		for (int eachID : BORDER_OUT) {
			Preset.addItem(eachID, OTHERBORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
		}
		Preset.addItem(4, Utils.addLore(Utils.newItem(Material.BLACK_STAINED_GLASS_PANE), " "),
				ChestMenuUtils.getEmptyClickHandler());
		Preset.addItem(5, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
		for (int i : getOutputSlots()) {
			Preset.addMenuClickHandler(i, new AdvancedMenuClickHandler() {

				@Override
				public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
					return false;
				}

				@Override
				public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor,
						ClickAction action) {
					return cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR;
				}
			});
		}
	}

	public void registerDefaultRecipes() {

	}

	@Override
	public io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType getEnergyComponentType() {
		return EnergyNetComponentType.CONSUMER;
	}

	@Override
	public int getCapacity() {
		return 0;
	}

	public List<ItemStack> getDisplayRecipes() {
		List<ItemStack> displayRecipes = new ArrayList<>(recipes.size() * 2);

		for (MachineRecipe recipe : recipes) {
			if (recipe.getInput().length != 1)
				continue;

			displayRecipes.add(recipe.getInput()[0]);
			displayRecipes.add(recipe.getOutput()[0]);
		}

		return displayRecipes;
	}

	public MachineRecipe getProcessing(Block b) {
		return processing.get(b);
	}

	public boolean isProcessing(Block b) {
		return getProcessing(b) != null;
	}

	public void registerRecipe(MachineRecipe recipe) {
		recipe.setTicks(recipe.getTicks() / getSpeed());
		recipes.add(recipe);
	}

	public void registerRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
		registerRecipe(new MachineRecipe(seconds, input, output));
	}

	@Override
	public void preRegister() {
		addItemHandler(new BlockTicker() {
			@Override
			public void tick(Block b, SlimefunItem sf, Config data) {
				ACraftingTable.this.tick(b);
			}

			@Override
			public boolean isSynchronized() {
				return false;
			}
		});
	}

	protected void tick(Block b) {
		new BukkitRunnable() {

			@Override
			public void run() {
				Bukkit.getPluginManager().callEvent(new MachineTickEvent(b));
			}
			
		}.runTask(ClayTech.plugin);
		BlockMenu inv = BlockStorage.getInventory(b);
		// 机器正在处理
		if (isProcessing(b)) {
			// 剩余时间
			int timeleft = progress.get(b);

			if (timeleft > 0) {
				// 还在处理
				ChestMenuUtils.updateProgressbar(inv, 4, timeleft, processing.get(b).getTicks(), getProgressBar());

				if (ChargableBlock.isChargable(b)) {
					if (ChargableBlock.getCharge(b) < getEnergyConsumption())
						return;
					ChargableBlock.addCharge(b, -getEnergyConsumption());
					progress.put(b, timeleft - 1);
				} else
					progress.put(b, timeleft - 1);
			} else {
				// 处理结束
				inv.replaceExistingItem(4, Utils.addLore(Utils.newItem(Material.BLACK_STAINED_GLASS_PANE), " "));

				for (ItemStack output : processing.get(b).getOutput()) {
					if (output != null)
						inv.pushItem(output.clone(), getOutputSlots());
				}

				progress.remove(b);
				processing.remove(b);
			}
		} else {
			// 没有在处理
			MachineRecipe r = null;
			Map<Integer, Integer> found = new HashMap<>();
			int i;
			for (MachineRecipe recipe : recipes) {
				i = 0;
				for (ItemStack input : recipe.getInput()) {
					if (SlimefunManager.isItemSimilar(inv.getItemInSlot(inputslots[i]), input, true)) {
						// 如果该位置的物品符合某合成配方的对应位置物品
						if (input != null) {
							found.put(inputslots[i], input.getAmount());
						}
					}
					if (inv.getItemInSlot(inputslots[i]) == input && input == null) {
						found.put(i, 0);
					}
					if (i < 8) {
						i++;
					} else
						i = 0;
				}
				if (found.size() == recipe.getInput().length) {
					r = recipe;
					break;
				} else
					found.clear();
			}

			if (r != null) {
				if (ChargableBlock.isChargable(b)) {
					if (ChargableBlock.getCharge(b) < getEnergyConsumption())
						return;
					ChargableBlock.addCharge(b, -getEnergyConsumption());
				}
				for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
					if (entry.getValue() > 0)
						inv.consumeItem(entry.getKey(), entry.getValue());
				}

				processing.put(b, r);
				progress.put(b, r.getTicks());
			}
		}
	}

}
