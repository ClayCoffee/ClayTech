package club.claycoffee.ClayTech.items;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.Machines.ANewContainer;
import club.claycoffee.ClayTech.api.listeners.MachineTickEvent;
import club.claycoffee.ClayTech.utils.Lang;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.LockedCategory;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class ElectricStoneCrusher extends ANewContainer {

	public ElectricStoneCrusher(LockedCategory category, SlimefunItemStack item, String id, RecipeType recipeType,
			ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);
	}

	@Override
	public String getInventoryTitle() {
		return Lang.readMachinesText("CLAY_ELECTRIC_STONE_CRUSHER");
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.REDSTONE_TORCH);
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
	public void registerDefaultRecipes() {
		this.registerRecipe(5, new ItemStack[] { new ItemStack(Material.COBBLESTONE) },
				new ItemStack[] { new ItemStack(Material.GRAVEL) });
	}

	@Override
	public int getCapacity() {
		return 128;
	}

	@Override
	public String getMachineIdentifier() {
		return "CLAY_ELECTRIC_STONE_CRUSHER";
	}
	
	@SuppressWarnings("deprecation")
	protected void tick(Block b) {
		Bukkit.getPluginManager().callEvent(new MachineTickEvent(b));
        BlockMenu inv = BlockStorage.getInventory(b);

        if (isProcessing(b)) {
            int timeleft = progress.get(b);

            if (timeleft > 0) {
                ChestMenuUtils.updateProgressbar(inv, 22, timeleft, processing.get(b).getTicks(), getProgressBar());

                if (ChargableBlock.isChargable(b)) {
                    if (ChargableBlock.getCharge(b) < getEnergyConsumption()) return;
                    ChargableBlock.addCharge(b, -getEnergyConsumption());
                    progress.put(b, timeleft - 1);
                }
                else progress.put(b, timeleft - 1);
            }
            else {
                inv.replaceExistingItem(22, new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "));

                for (ItemStack output : processing.get(b).getOutput()) {
                    inv.pushItem(output.clone(), getOutputSlots());
                }

                progress.remove(b);
                processing.remove(b);
            }
        }
        else {
            MachineRecipe r = null;
            Map<Integer, Integer> found = new HashMap<>();

            for (MachineRecipe recipe : recipes) {
                for (ItemStack input : recipe.getInput()) {
                    for (int slot : getInputSlots()) {
                        if (SlimefunManager.isItemSimilar(inv.getItemInSlot(slot), input, true)) {
                            found.put(slot, input.getAmount());
                            break;
                        }
                    }
                }
                if (found.size() == recipe.getInput().length) {
                    r = recipe;
                    break;
                }
                else found.clear();
            }

            if (r != null) {
                if (!fits(b, r.getOutput())) return;

                for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
                    inv.consumeItem(entry.getKey(), entry.getValue());
                }

                processing.put(b, r);
                progress.put(b, r.getTicks());
            }
        }
    }
}
