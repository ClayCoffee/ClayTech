package club.claycoffee.ClayTech.items;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.Machines.AExtracter;
import club.claycoffee.ClayTech.api.listeners.MachineTickEvent;
import club.claycoffee.ClayTech.api.listeners.PlayerExtractElementEvent;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Utils;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.LockedCategory;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ElementExtracter extends AExtracter {
	private ItemStack[] inputItem;
	private ItemStack outputItem;

	public ElementExtracter(LockedCategory category, SlimefunItemStack item, String id, RecipeType recipeType,
			ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);
	}

	public String getInventoryTitle() {
		return Lang.readMachinesText("CLAY_ELEMENT_EXTRACTER");
	}

	public ItemStack getProgressBar() {
		return new ItemStack(Material.REDSTONE_TORCH);
	}

	public int getEnergyConsumption() {
		return 64;
	}

	public int getSpeed() {
		return 1;
	}

	@Override
	public int getCapacity() {
		return 1024;
	}

	public String getMachineIdentifier() {
		return "ELEMENT_EXTRACTER";
	}

	public void registerDefaultRecipes() {
		registerRecipe(10, new ItemStack[] { new ItemStack(Material.DIRT, 3) },
				new ItemStack[] { ClayTechItems.ELEMENT_OXYGEN });
		registerRecipe(10, new ItemStack[] { new ItemStack(Material.COAL, 8) },
				new ItemStack[] { ClayTechItems.ELEMENT_CARBON });
		registerRecipe(10, new ItemStack[] { new ItemStack(Material.SAND, 10) },
				new ItemStack[] { ClayTechItems.ELEMENT_SILICON });
	}

	protected void tick(Block b) {
		Bukkit.getPluginManager().callEvent(new MachineTickEvent(b));
		BlockMenu inv = BlockStorage.getInventory(b);
		// 机器正在处理
		if (isProcessing(b)) {
			// 剩余时间
			int timeleft = progress.get(b);

			if (timeleft > 0) {
				// 还在处理
				ChestMenuUtils.updateProgressbar(inv, 22, timeleft, processing.get(b).getTicks(), getProgressBar());

				if (ChargableBlock.isChargable(b)) {
					if (ChargableBlock.getCharge(b) < getEnergyConsumption())
						return;
					ChargableBlock.addCharge(b, -getEnergyConsumption());
					progress.put(b, timeleft - 1);
				} else
					progress.put(b, timeleft - 1);
			} else {
				// 处理结束
				inv.replaceExistingItem(22, Utils.addLore(Utils.newItem(Material.PINK_STAINED_GLASS_PANE), " "));
				new BukkitRunnable() {

					@Override
					public void run() {
						Bukkit.getPluginManager().callEvent(new PlayerExtractElementEvent(b, inputItem, outputItem));

					}

				}.runTask(ClayTech.plugin);
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
			for (MachineRecipe recipe : recipes) {
				ItemStack input = recipe.getInput()[0];
				if (SlimefunManager.isItemSimilar(inv.getItemInSlot(inputslots[0]), input, true)) {
					if (input != null) {
						found.put(inputslots[0], input.getAmount());
					}
				}
				if (found.size() == recipe.getInput().length) {
					r = recipe;
					break;
				} else {
					found.clear();
				}
			}

			if (r != null) {
				if (ChargableBlock.isChargable(b)) {
					if (ChargableBlock.getCharge(b) < getEnergyConsumption())
						return;
					ChargableBlock.addCharge(b, -getEnergyConsumption());
				}
				if (!SlimefunManager.isItemSimilar(inv.getItemInSlot(40), ClayTechItems.ELEMENT_UNIT, true))
					return;
				for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
					if (entry.getValue() > 0) {
						inv.consumeItem(entry.getKey(), entry.getValue());
						inv.consumeItem(40, 1);
					}

				}
				inputItem = r.getInput();
				outputItem = r.getOutput()[0];
				processing.put(b, r);
				progress.put(b, r.getTicks());
			}
		}
	}
}
