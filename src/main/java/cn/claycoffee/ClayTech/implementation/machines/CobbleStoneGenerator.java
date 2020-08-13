package cn.claycoffee.ClayTech.implementation.machines;

import cn.claycoffee.ClayTech.utils.Lang;
import cn.claycoffee.ClayTech.utils.Utils;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.InventoryBlock;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CobbleStoneGenerator extends SlimefunItem implements InventoryBlock, EnergyNetComponent {
    public final static int[] inputslots = new int[]{};
    public final static int[] outputslots = new int[]{22};
    private static final int[] BORDER_A = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41,
            42, 43, 44, 12, 14, 21, 23, 30, 31, 32};
    private static final int[] BORDER_B = {10, 11, 15, 16, 19, 20, 24, 25, 28, 29, 33, 34};
    private static final ItemStack BORDER_A_ITEM = Utils.newItemD(Material.LIGHT_BLUE_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final ItemStack BORDER_B_ITEM = Utils.newItemD(Material.LIME_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    public static Map<Block, MachineRecipe> processing = new HashMap<Block, MachineRecipe>();
    public static Map<Block, Integer> progress = new HashMap<Block, Integer>();
    protected final List<MachineRecipe> recipes = new ArrayList<MachineRecipe>();

    public CobbleStoneGenerator(Category category, SlimefunItemStack item, String id, RecipeType recipeType,
                                ItemStack[] recipe) {
        super(category, item, id, recipeType, recipe);

        createPreset(this, getInventoryTitle(), this::SetupMenu);

        registerBlockHandler(id, (p, b, tool, reason) -> {
            BlockMenu inv = BlockStorage.getInventory(b);
            if (inv != null) {
                for (int slot : getInputSlots()) {
                    if (inv.getItemInSlot(slot) != null) {
                        if (inv.getItemInSlot(slot).getType() != Material.BEDROCK) {
                            b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                            inv.replaceExistingItem(slot, null);
                        }
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
    }

    private String getInventoryTitle() {
        return Lang.readMachinesText("CLAY_COBBLESTONE_GENERATOR");
    }

    @Override
    public int getCapacity() {
        return 256;
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{22};
    }

    public void SetupMenu(BlockMenuPreset Preset) {
        for (int eachID : BORDER_A) {
            Preset.addItem(eachID, BORDER_A_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        }
        for (int eachID : BORDER_B) {
            Preset.addItem(eachID, BORDER_B_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        }
        Preset.addItem(13, Utils.addLore(Utils.newItem(Material.BLACK_STAINED_GLASS_PANE), " "),
                ChestMenuUtils.getEmptyClickHandler());
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

    @Override
    public int[] getInputSlots() {
        return new int[]{};
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                CobbleStoneGenerator.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
    }

    protected void tick(Block b) {
        BlockMenu inv = BlockStorage.getInventory(b);
        // 机器正在处理
        if (isProcessing(b)) {
            // 剩余时间
            int timeleft = progress.get(b);

            if (timeleft > 0) {
                // 还在处理
                ChestMenuUtils.updateProgressbar(inv, 13, timeleft, processing.get(b).getTicks(), getProgressBar());

                if (ChargableBlock.isChargable(b)) {
                    if (ChargableBlock.getCharge(b) < getEnergyConsumption())
                        return;
                    ChargableBlock.addCharge(b, -getEnergyConsumption());
                    progress.put(b, timeleft - 1);
                } else
                    progress.put(b, timeleft - 1);
            } else {
                // 处理结束
                inv.replaceExistingItem(13, Utils.addLore(Utils.newItem(Material.BLACK_STAINED_GLASS_PANE), " "));

                for (ItemStack output : processing.get(b).getOutput()) {
                    if (output != null)
                        inv.pushItem(output.clone(), getOutputSlots());
                }

                progress.remove(b);
                processing.remove(b);
            }
        } else {
            // 没有在处理
            MachineRecipe r = new MachineRecipe(1, new ItemStack[]{},
                    new ItemStack[]{new ItemStack(Material.COBBLESTONE)});
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

            processing.put(b, r);
            progress.put(b, r.getTicks());
        }
    }

    private ItemStack getProgressBar() {
        return new ItemStack(Material.MOSSY_COBBLESTONE);
    }

    public int getEnergyConsumption() {
        return 50;
    }

    public MachineRecipe getProcessing(Block b) {
        return processing.get(b);
    }

    public boolean isProcessing(Block b) {
        return getProcessing(b) != null;
    }

}
