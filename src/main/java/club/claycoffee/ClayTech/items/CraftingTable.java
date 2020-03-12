package club.claycoffee.ClayTech.items;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.Recipes;
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
		this.registerRecipe(20, Recipes.BLIND_CORE, new ItemStack[] { Defines.BLIND_CORE });
		this.registerRecipe(100, Recipes.BLIND_SWORD, new ItemStack[] { Defines.BLIND_SWORD });
		this.registerRecipe(20, Recipes.POISON_EYE, new ItemStack[] { Defines.POISON_EYE });
		this.registerRecipe(20, Recipes.POISON_CORE, new ItemStack[] { Defines.POISON_CORE });
		this.registerRecipe(40, Recipes.ADVANCED_CONFUSION_CORE, new ItemStack[] { Defines.ADVANCED_POISON_CORE });
		this.registerRecipe(20, Recipes.CONFUSION_CORE, new ItemStack[] { Defines.CONFUSION_CORE });
		this.registerRecipe(40, Recipes.ADVANCED_CONFUSION_CORE, new ItemStack[] { Defines.ADVANCED_CONFUSION_CORE });
		this.registerRecipe(20, Recipes.BLACK_ROCK_BLOCK, new ItemStack[] { Defines.BLACK_ROCK_BLOCK });
		this.registerRecipe(20, Recipes.SLOWNESS_CORE, new ItemStack[] { Defines.SLOWNESS_CORE });
		this.registerRecipe(40, Recipes.ADVANCED_SLOWNESS_CORE, new ItemStack[] { Defines.ADVANCED_SLOWNESS_CORE });
		this.registerRecipe(40, Recipes.ADVANCED_BLIND_CORE, new ItemStack[] { Defines.ADVANCED_BLIND_CORE });
		this.registerRecipe(400, Recipes.FOUR_BOW, new ItemStack[] { Defines.FOUR_BOW });
		this.registerRecipe(100, Recipes.POISON_SWORD, new ItemStack[] { Defines.POISON_SWORD });
		this.registerRecipe(100, Recipes.ANTI_SLOWNESS_BOOTS, new ItemStack[] { Defines.ANTI_SLOWNESS_BOOTS });
		this.registerRecipe(80, Recipes.BLISTERING_CORE, new ItemStack[] { Defines.BLISTERING_CORE });
		this.registerRecipe(30, Recipes.ELEMENT_UNIT, new ItemStack[] { Defines.ELEMENT_UNIT });
		this.registerRecipe(8, Recipes.HIGHSPEED_RAILWAY, new ItemStack[] { Defines.HIGHSPEED_RAILWAY });
		ItemStack elem8 = Defines.ELECTRIC_MOTOR_8;
		elem8.setAmount(8);
		this.registerRecipe(8, Recipes.ELECTRIC_MOTOR_8, new ItemStack[] { elem8 });
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

				for (ItemStack output : processing.get(b).getOutput()) {
					if (output != null)
						inv.pushItem(output.clone(), getOutputSlots());
				}
				MetadataValue md = b.getMetadata("currentRecipe").get(0);
				Bukkit.getPluginManager().callEvent(
						new PlayerCraftItemEvent(b, (ItemStack[]) md.value(), processing.get(b).getOutput()[0]));
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
					b.setMetadata("currentRecipe", new FixedMetadataValue(ClayTech.plugin, recipe.getInput()));
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
