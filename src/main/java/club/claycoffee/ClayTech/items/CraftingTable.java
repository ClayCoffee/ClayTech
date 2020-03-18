package club.claycoffee.ClayTech.items;

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
import club.claycoffee.ClayTech.Machines.ACraftingTable;
import club.claycoffee.ClayTech.api.listeners.PlayerCraftItemEvent;
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

public class CraftingTable extends ACraftingTable {
	private ItemStack[] inputItem;
	private ItemStack outputItem;

	public CraftingTable(LockedCategory category, SlimefunItemStack item, String id, RecipeType recipeType,
			ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);
	}

	@Override
	public String getInventoryTitle() {
		return Lang.readMachinesText("CLAY_FUSION_MACHINE");
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.CRAFTING_TABLE);
	}

	@Override
	public int getEnergyConsumption() {
		return 16;
	}

	@Override
	public int getSpeed() {
		return 1;
	}

	@Override
	public String getMachineIdentifier() {
		return "CLAY_CRAFTING_TABLE";
	}

	@Override
	public int getCapacity() {
		return 128;
	}

	@Override
	public void registerDefaultRecipes() {
		this.registerRecipe(20, ClayTechMachineRecipes.BLIND_CORE, new ItemStack[] { ClayTechItems.BLIND_CORE });
		this.registerRecipe(100, ClayTechMachineRecipes.BLIND_SWORD, new ItemStack[] { ClayTechItems.BLIND_SWORD });
		this.registerRecipe(20, ClayTechMachineRecipes.POISON_EYE, new ItemStack[] { ClayTechItems.POISON_EYE });
		this.registerRecipe(20, ClayTechMachineRecipes.POISON_CORE, new ItemStack[] { ClayTechItems.POISON_CORE });
		this.registerRecipe(40, ClayTechMachineRecipes.ADVANCED_CONFUSION_CORE,
				new ItemStack[] { ClayTechItems.ADVANCED_POISON_CORE });
		this.registerRecipe(20, ClayTechMachineRecipes.CONFUSION_CORE,
				new ItemStack[] { ClayTechItems.CONFUSION_CORE });
		this.registerRecipe(40, ClayTechMachineRecipes.ADVANCED_CONFUSION_CORE,
				new ItemStack[] { ClayTechItems.ADVANCED_CONFUSION_CORE });
		this.registerRecipe(20, ClayTechMachineRecipes.BLACK_ROCK_BLOCK,
				new ItemStack[] { ClayTechItems.BLACK_ROCK_BLOCK });
		this.registerRecipe(20, ClayTechMachineRecipes.SLOWNESS_CORE, new ItemStack[] { ClayTechItems.SLOWNESS_CORE });
		this.registerRecipe(40, ClayTechMachineRecipes.ADVANCED_SLOWNESS_CORE,
				new ItemStack[] { ClayTechItems.ADVANCED_SLOWNESS_CORE });
		this.registerRecipe(40, ClayTechMachineRecipes.ADVANCED_BLIND_CORE,
				new ItemStack[] { ClayTechItems.ADVANCED_BLIND_CORE });
		this.registerRecipe(400, ClayTechMachineRecipes.FOUR_BOW, new ItemStack[] { ClayTechItems.FOUR_BOW });
		this.registerRecipe(100, ClayTechMachineRecipes.POISON_SWORD, new ItemStack[] { ClayTechItems.POISON_SWORD });
		this.registerRecipe(100, ClayTechMachineRecipes.ANTI_SLOWNESS_BOOTS,
				new ItemStack[] { ClayTechItems.ANTI_SLOWNESS_BOOTS });
		this.registerRecipe(80, ClayTechMachineRecipes.BLISTERING_CORE,
				new ItemStack[] { ClayTechItems.BLISTERING_CORE });
		this.registerRecipe(30, ClayTechMachineRecipes.ELEMENT_UNIT, new ItemStack[] { ClayTechItems.ELEMENT_UNIT });
		this.registerRecipe(8, ClayTechMachineRecipes.HIGHSPEED_RAILWAY,
				new ItemStack[] { ClayTechItems.HIGHSPEED_RAILWAY });
		ItemStack elem8 = ClayTechItems.ELECTRIC_MOTOR_8;
		elem8.setAmount(8);
		this.registerRecipe(8, ClayTechMachineRecipes.ELECTRIC_MOTOR_8, new ItemStack[] { elem8 });

		this.registerRecipe(50, ClayTechMachineRecipes.TNT_EXPLOSION_CREATER,
				new ItemStack[] { ClayTechItems.TNT_EXPLOSION_CREATER });
		this.registerRecipe(180, ClayTechMachineRecipes.REINFORCED_ALLOY_PICKAXE,
				new ItemStack[] { ClayTechItems.REINFORCED_ALLOY_PICKAXE });
		this.registerRecipe(40, ClayTechMachineRecipes.CLAY_FUSION_INGOT,
				new ItemStack[] { ClayTechItems.CLAY_FUSION_INGOT });
		this.registerRecipe(50, ClayTechMachineRecipes.CLAY_ALLOY_INGOT,
				new ItemStack[] { ClayTechItems.CLAY_ALLOY_INGOT });
		this.registerRecipe(300, ClayTechMachineRecipes.CLAY_ALLOY_PICKAXE,
				new ItemStack[] { ClayTechItems.CLAY_ALLOY_PICKAXE });
		this.registerRecipe(300, ClayTechMachineRecipes.CLAY_ALLOY_HELMET,
				new ItemStack[] { ClayTechItems.CLAY_ALLOY_HELMET });
		this.registerRecipe(300, ClayTechMachineRecipes.CLAY_ALLOY_CHESTPLATE,
				new ItemStack[] { ClayTechItems.CLAY_ALLOY_CHESTPLATE });
		this.registerRecipe(300, ClayTechMachineRecipes.CLAY_ALLOY_LEGGINGS,
				new ItemStack[] { ClayTechItems.CLAY_ALLOY_LEGGINGS });
		this.registerRecipe(300, ClayTechMachineRecipes.CLAY_ALLOY_BOOTS,
				new ItemStack[] { ClayTechItems.CLAY_ALLOY_BOOTS });
	}

	@Override
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

				new BukkitRunnable() {

					@Override
					public void run() {
						Bukkit.getPluginManager().callEvent(new PlayerCraftItemEvent(b, inputItem, outputItem));

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
				inputItem = r.getInput();
				outputItem = r.getOutput()[0];
				processing.put(b, r);
				progress.put(b, r.getTicks());
			}
		}
	}

}
