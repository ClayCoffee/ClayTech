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
import club.claycoffee.ClayTech.api.listeners.MachineTickEvent;
import club.claycoffee.ClayTech.api.listeners.PlayerCookItemEvent;
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

public class FoodCauldron extends ACraftingTable {

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

		this.registerRecipe(30, Recipes.CHICKEN_FOOT, new ItemStack[] { Defines.CHICKEN_FOOT });
		this.registerRecipe(30, Recipes.SPICY_CHICKEN_BURGER, new ItemStack[] { Defines.SPICY_CHICKEN_BURGER });
		this.registerRecipe(30, Recipes.BABA_BURGER, new ItemStack[] { Defines.BABA_BURGER });
		this.registerRecipe(30, Recipes.CHOCOLATE, new ItemStack[] { Defines.CHOCOLATE });
		this.registerRecipe(30, Recipes.SNAIL_FOOD, new ItemStack[] { Defines.SNAIL_FOOD });
		this.registerRecipe(10, Recipes.HONEY_SWEET, new ItemStack[] { Defines.HONEY_SWEET });

		this.registerRecipe(30, Recipes.CLAY_COFFEE, new ItemStack[] { Defines.CLAY_COFFEE });
		this.registerRecipe(30, Recipes.LEMON_POWDER_DRINK, new ItemStack[] { Defines.LEMON_POWDER_DRINK });
		this.registerRecipe(30, Recipes.TEA_DRINK, new ItemStack[] { Defines.TEA_DRINK });
		this.registerRecipe(30, Recipes.LEMON_TEA_DRINK, new ItemStack[] { Defines.LEMON_TEA_DRINK });
	}

	@Override
	public int getCapacity() {
		return 512;
	}

	@Override
	protected void tick(Block b) {
		Bukkit.getPluginManager().callEvent(new MachineTickEvent(b));
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
						new PlayerCookItemEvent(b, (ItemStack[]) md.value(), processing.get(b).getOutput()[0]));
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