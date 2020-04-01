package club.claycoffee.ClayTech.implementation.abstractMachines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Utils;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
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
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

@SuppressWarnings("deprecation")
public abstract class AExperimentTable extends SlimefunItem implements InventoryBlock, EnergyNetComponent {
	public final static int[] inputslots = new int[] { 20, 21, 22, 23, 24 };
	public final static int[] outputslots = new int[] { 40 };
	public static Map<Block, MachineRecipe> processing = new HashMap<>();
	public static Map<Block, Integer> progress = new HashMap<>();
	protected final List<MachineRecipe> recipes = new ArrayList<>();
	private static final int[] BORDER_A = { 0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 25, 26,
			27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 41, 52, 53 };
	private static final int[] BORDER_B = { 37, 38, 39, 41, 42, 43 };
	private static final ItemStack FREE_STATE_ITEM = Utils.newItemD(Material.BLACK_STAINED_GLASS_PANE,
			Lang.readMachinesText("SPLIT_LINE"));
	private static final ItemStack BORDER_A_ITEM = Utils.newItemD(Material.LIGHT_BLUE_STAINED_GLASS_PANE,
			Lang.readMachinesText("SPLIT_LINE"));
	private static final ItemStack BORDER_B_ITEM = Utils.newItemD(Material.LIME_STAINED_GLASS_PANE,
			Lang.readMachinesText("SPLIT_LINE"));

	public AExperimentTable(Category category, SlimefunItemStack item, String id, RecipeType recipeType,
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

	public abstract String getInventoryTitle();

	public abstract ItemStack getProgressBar();

	public abstract int getEnergyConsumption();

	public abstract int getSpeed();

	public abstract String getMachineIdentifier();

	@Override
	public void preRegister() {
		addItemHandler(new BlockTicker() {
			@Override
			public void tick(Block b, SlimefunItem sf, Config data) {
				AExperimentTable.this.tick(b);
			}

			@Override
			public boolean isSynchronized() {
				return false;
			}
		});
	}

	public void SetupMenu(BlockMenuPreset Preset) {
		// TODO Change
		Preset.addItem(4, FREE_STATE_ITEM, ChestMenuUtils.getEmptyClickHandler());
		for (int eachID : BORDER_A) {
			Preset.addItem(eachID, BORDER_A_ITEM, ChestMenuUtils.getEmptyClickHandler());
		}
		for (int eachID : BORDER_B) {
			Preset.addItem(eachID, BORDER_B_ITEM, ChestMenuUtils.getEmptyClickHandler());
		}
		Preset.addItem(4, Utils.addLore(Utils.newItem(Material.BLACK_STAINED_GLASS_PANE), " "),
				ChestMenuUtils.getEmptyClickHandler());
		Preset.addItem(4, FREE_STATE_ITEM, ChestMenuUtils.getEmptyClickHandler());
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
	public EnergyNetComponentType getEnergyComponentType() {
		return EnergyNetComponentType.CONSUMER;
	}

	@Override
	public int getCapacity() {
		return 1024;
	}

	@Override
	public int[] getInputSlots() {
		return inputslots;
	}

	@Override
	public int[] getOutputSlots() {
		return outputslots;
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

	protected void tick(Block b) {
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
						// 如果该位置的物品符合某实验配方的对应位置物品
						if (input != null) {
							found.put(inputslots[i], input.getAmount());
						}
					}
					if (inv.getItemInSlot(inputslots[i]) == input && input == null) {
						found.put(i, 0);
					}
					if (i < 4) {
						i++;
					} else
						i = 0;
				}
				if (found.size() == 5) {
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
				if (inv.getItemInSlot(outputslots[0]) != null) {
					ItemStack is = inv.getItemInSlot(outputslots[0]);
					if (is.getMaxStackSize() == is.getAmount())
						return;
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
