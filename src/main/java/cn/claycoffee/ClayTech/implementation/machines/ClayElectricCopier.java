package cn.claycoffee.ClayTech.implementation.machines;

import cn.claycoffee.ClayTech.implementation.abstractMachines.ANewContainer;
import cn.claycoffee.ClayTech.utils.Lang;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: ClayTech
 * @Author: ClayCoffee
 * @Date: 2020/7/20 11:35
 * @Email: 1020757140@qq.com
 * AGPL 3.0
 */

public class ClayElectricCopier extends ANewContainer {
    private int mode;
    private static Map<Block, ItemStack> source = new HashMap<>();
    private static Map<Block, ItemStack> copy = new HashMap<>();

    public ClayElectricCopier(Category category, SlimefunItemStack item, String id, RecipeType recipeType,
                              ItemStack[] recipe) {
        super(category, item, id, recipeType, recipe);
    }

    @Override
    public String getInventoryTitle() {
        return Lang.readMachinesText("CLAY_ELECTRIC_COPIER");
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.BOOK);
    }

    @Override
    public int getEnergyConsumption() {
        return 40;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public String getMachineIdentifier() {
        return "CLAY_ELECTRIC_COPIER";
    }

    @Override
    public int getCapacity() {
        return 256;
    }

    protected void tick(Block b) {
        BlockMenu inv = BlockStorage.getInventory(b);
        if (isProcessing(b)) {
            int timeleft = pt.get(b);

            if (timeleft > 0) {
                ChestMenuUtils.updateProgressbar(inv, 22, timeleft, pr.get(b).getTicks(), getProgressBar());

                if (isChargeable()) {
                    if (getCharge(b.getLocation()) < getEnergyConsumption())
                        return;
                    removeCharge(b.getLocation(), getEnergyConsumption());
                    pt.put(b, timeleft - 1);
                } else
                    pt.put(b, timeleft - 1);
            } else {
                if (inv.getItemInSlot(getOutputSlots()[0]) != null || inv.getItemInSlot(getOutputSlots()[1]) != null)
                    return;

                inv.replaceExistingItem(22, new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "));

                if (mode == 1) {
                    inv.pushItem(source.get(b), getOutputSlots());
                    inv.pushItem(source.get(b), getOutputSlots());
                } else if (mode == 2) {
                    BookMeta sourceMeta = (BookMeta) source.get(b).getItemMeta();
                    BookMeta copyMeta = (BookMeta) copy.get(b).getItemMeta();
                    copyMeta.setPages(sourceMeta.getPages());
                    copyMeta.setGeneration(BookMeta.Generation.COPY_OF_ORIGINAL);
                    ItemStack c = copy.get(b);
                    c.setItemMeta(copyMeta);
                    copy.put(b,c);
                    inv.pushItem(source.get(b), getOutputSlots());
                    inv.pushItem(copy.get(b), getOutputSlots());
                }
                pt.remove(b);
                pr.remove(b);
                source.remove(b);
                copy.remove(b);
            }
        } else {
            if (inv.getItemInSlot(19) == null || inv.getItemInSlot(20) == null) return;
            if (inv.getItemInSlot(19).getType() == Material.WRITABLE_BOOK && inv.getItemInSlot(20).getType() == Material.WRITABLE_BOOK) {
                // Mode I
                mode = 1;
                source.put(b,inv.getItemInSlot(19).clone());
                copy.put(b,inv.getItemInSlot(20).clone());
            } else if (inv.getItemInSlot(19).getType() == Material.WRITTEN_BOOK && inv.getItemInSlot(20).getType() == Material.WRITABLE_BOOK) {
                // Mode II
                mode = 2;
                source.put(b,inv.getItemInSlot(19).clone());
                copy.put(b,inv.getItemInSlot(20).clone());
            } else if (inv.getItemInSlot(19).getType() == Material.WRITABLE_BOOK && inv.getItemInSlot(20).getType() == Material.WRITTEN_BOOK) {
                // Mode II
                mode = 2;
                source.put(b,inv.getItemInSlot(19).clone());
                copy.put(b,inv.getItemInSlot(20).clone());
            } else mode = 0;
            if (mode > 0) {
                BookMeta sourceMeta = (BookMeta) source.get(b).getItemMeta();
                MachineRecipe r = new MachineRecipe(sourceMeta.getPages().size() * 4, null, null);
                pt.put(b, r.getTicks());
                pr.put(b, r);
                inv.consumeItem(19);
                inv.consumeItem(20);
                return;
            }
        }
    }
}
