package club.claycoffee.ClayTech.implementation.machines;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.ClayTechMachineRecipes;
import club.claycoffee.ClayTech.api.listeners.MachineTickEvent;
import club.claycoffee.ClayTech.api.listeners.PlayerCookItemEvent;
import club.claycoffee.ClayTech.implementation.abstractMachines.ACraftingTable;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Utils;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.LockedCategory;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

public class FoodCauldron extends ACraftingTable {
	private ItemStack[] inputItem;
	private ItemStack outputItem;

	public FoodCauldron(LockedCategory category, SlimefunItemStack item, String id, RecipeType recipeType,
			ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);
	}

	@Override
	public String getInventoryTitle() {
		return Lang.readMachinesText("CLAY_FOOD_CAULDRON");
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.CAMPFIRE);
	}

	@Override
	public int getEnergyConsumption() {
		return 32;
	}

	@Override
	public int getSpeed() {
		return 1;
	}

	@Override
	public String getMachineIdentifier() {
		return "CLAY_FOOD_CAULDRON";
	}

	@Override
	public void registerDefaultRecipes() {

		this.registerRecipe(30, ClayTechMachineRecipes.CHICKEN_FOOT, new ItemStack[] { ClayTechItems.CHICKEN_FOOT });
		this.registerRecipe(30, ClayTechMachineRecipes.SPICY_CHICKEN_BURGER,
				new ItemStack[] { ClayTechItems.SPICY_CHICKEN_BURGER });
		this.registerRecipe(30, ClayTechMachineRecipes.BABA_BURGER, new ItemStack[] { ClayTechItems.BABA_BURGER });
		this.registerRecipe(30, ClayTechMachineRecipes.CHOCOLATE, new ItemStack[] { ClayTechItems.CHOCOLATE });
		this.registerRecipe(30, ClayTechMachineRecipes.SNAIL_FOOD, new ItemStack[] { ClayTechItems.SNAIL_FOOD });
		this.registerRecipe(10, ClayTechMachineRecipes.HONEY_SWEET, new ItemStack[] { ClayTechItems.HONEY_SWEET });
		this.registerRecipe(15, ClayTechMachineRecipes.COOKED_SWEET_POTATO,
				new ItemStack[] { ClayTechItems.COOKED_SWEET_POTATO });

		this.registerRecipe(30, ClayTechMachineRecipes.CLAY_COFFEE, new ItemStack[] { ClayTechItems.CLAY_COFFEE });
		this.registerRecipe(30, ClayTechMachineRecipes.LEMON_POWDER_DRINK,
				new ItemStack[] { ClayTechItems.LEMON_POWDER_DRINK });
		this.registerRecipe(30, ClayTechMachineRecipes.TEA_DRINK, new ItemStack[] { ClayTechItems.TEA_DRINK });
		this.registerRecipe(30, ClayTechMachineRecipes.LEMON_TEA_DRINK,
				new ItemStack[] { ClayTechItems.LEMON_TEA_DRINK });
	}

	@Override
	public int getCapacity() {
		return 512;
	}

	@Override
	protected void tick(Block b) {
		new BukkitRunnable() {

			@Override
			public void run() {
				Bukkit.getPluginManager().callEvent(new MachineTickEvent(b));
			}

		}.runTask(ClayTech.getInstance());
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
				new BukkitRunnable() {

					@Override
					public void run() {
						Bukkit.getPluginManager().callEvent(new PlayerCookItemEvent(b, inputItem, outputItem));

					}

				}.runTask(ClayTech.getInstance());
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
					if (SlimefunUtils.isItemSimilar(inv.getItemInSlot(inputslots[i]), input, true)) {
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
				if (inv.getItemInSlot(outputslots[0]) != null) {
					ItemStack is = inv.getItemInSlot(outputslots[0]);
					if (is.getMaxStackSize() == is.getAmount())
						return;
				}
				for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
					if (entry.getValue() > 0)
						inv.consumeItem(entry.getKey(), entry.getValue());
				}
				inputItem = r.getInput();
				outputItem = r.getOutput()[0];
				processing.put(b, r);
				progress.put(b, r.getTicks());
			}
		}
	}
}