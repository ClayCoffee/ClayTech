package cn.claycoffee.ClayTech.implementation.abstractMachines;

import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.InventoryBlock;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class ANewContainer extends AContainer implements InventoryBlock, EnergyNetComponent {
    public ANewContainer(Category category, SlimefunItemStack item, String id, RecipeType recipeType,
                         ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    protected void tick(Block b) {
        BlockMenu inv = BlockStorage.getInventory(b);

        if (isProcessing(b)) {
            int timeleft = progress.get(b);

            if (timeleft > 0) {
                ChestMenuUtils.updateProgressbar(inv, 22, timeleft, processing.get(b).getTicks(), getProgressBar());

                if (getCapacity() > 0) {
                    if (ChargableBlock.getCharge(b) < getEnergyConsumption()) {
                        return;
                    }

                    ChargableBlock.addCharge(b, -getEnergyConsumption());
                    progress.put(b, timeleft - 1);
                } else {
                    progress.put(b, timeleft - 1);
                }
            } else {
                inv.replaceExistingItem(22, new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "));

                for (ItemStack output : processing.get(b).getOutput()) {
                    inv.pushItem(output.clone(), getOutputSlots());
                }

                progress.remove(b);
                processing.remove(b);
            }
        } else {
            MachineRecipe r = null;
            Map<Integer, Integer> found = new HashMap<>();

            for (MachineRecipe recipe : recipes) {
                for (ItemStack input : recipe.getInput()) {
                    for (int slot : getInputSlots()) {
                        if (SlimefunUtils.isItemSimilar(inv.getItemInSlot(slot), input, true)) {
                            if (input == null) {
                                found.put(slot, 1);
                            } else {
                                found.put(slot, input.getAmount());
                            }
                            break;
                        }
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
                for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
                    inv.consumeItem(entry.getKey(), entry.getValue());
                }

                processing.put(b, r);
                progress.put(b, r.getTicks());
            }
        }
    }
}