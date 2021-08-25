package cn.claycoffee.ClayTech.implementation.abstractMachines;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.api.events.PlayerAssembleEvent;
import cn.claycoffee.ClayTech.api.events.PlayerCraftItemEvent;
import cn.claycoffee.ClayTech.utils.Lang;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
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
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ACraftingTable extends SlimefunItem implements InventoryBlock, EnergyNetComponent, MachineProcessHolder<CraftingOperation> {
    protected static final int[] inputSlots = new int[]{19, 20, 21, 28, 29, 30, 37, 38, 39};
    protected static final int[] outputSlots = new int[]{34};
    private static final int[] BORDER = {0, 1, 2, 3, 5, 6, 7, 8, 14, 15, 16, 17, 23, 41, 43, 50, 51, 52, 53, 32};
    private static final int[] BORDER_IN = {9, 10, 11, 12, 13, 18, 22, 27, 31, 36, 40, 45, 46, 47, 48, 49};
    private static final int[] BORDER_OUT = {24, 25, 26, 33, 35, 42, 43, 44};


    private static final ItemStack BORDER_ITEM = new CustomItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final ItemStack BORDER_2_ITEM = new CustomItem(Material.LIME_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));

    private final MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this);
    protected static final Map<Block, MachineRecipe> processing = new HashMap<>();
    protected static final Map<Block, Integer> progress = new HashMap<>();
    protected final List<MachineRecipe> recipes = new ArrayList<>();

    public ACraftingTable(Category category, SlimefunItemStack item, RecipeType recipeType,
                          ItemStack[] recipe) {

        super(category, item, recipeType, recipe);

        processor.setProgressBar(getProgressBar());
        createPreset(this, getInventoryTitle(), this::constructMenu);

        addItemHandler(onBlockBreak());
    }

    protected BlockBreakHandler onBlockBreak() {
        return new SimpleBlockBreakHandler() {

            @Override
            public void onBlockBreak(@NotNull Block b) {
                BlockMenu inv = BlockStorage.getInventory(b);

                if (inv != null) {
                    inv.dropItems(b.getLocation(), getInputSlots());
                    inv.dropItems(b.getLocation(), getOutputSlots());
                }

                processor.endOperation(b);
            }

        };
    }

    public int[] getInputSlots() {
        return inputSlots;
    }

    @Override
    public int[] getOutputSlots() {
        return outputSlots;
    }

    public abstract String getInventoryTitle();

    public abstract ItemStack getProgressBar();

    public abstract int getEnergyConsumption();

    public abstract int getSpeed();

    public abstract String getMachineIdentifier();

    public void constructMenu(BlockMenuPreset preset) {
        preset.addItem(5, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
        for (int eachID : BORDER) {
            preset.addItem(eachID, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        for (int eachID : BORDER_IN) {
            preset.addItem(eachID, BORDER_2_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        for (int eachID : BORDER_OUT) {
            preset.addItem(eachID, BORDER_2_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        preset.addItem(4, new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "),
                ChestMenuUtils.getEmptyClickHandler());
        preset.addItem(5, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());

        for (int i : getOutputSlots()) {
            preset.addMenuClickHandler(i, new AdvancedMenuClickHandler() {

                @Override
                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
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
        return 0;
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
        if (input.length > 9) {
            if (output[0].hasItemMeta()) {
                Bukkit.getLogger()
                        .warning("There is an error when registering the recipe.Please contact the author.Error recipe:"
                                + output[0].getItemMeta().getDisplayName());
                return;
            } else {
                Bukkit.getLogger()
                        .warning("There is an error when registering the recipe.Please contact the author.Error recipe:"
                                + output[0].getType());
                return;
            }
        }
        registerRecipe(new MachineRecipe(seconds, input, output));
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                ACraftingTable.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
    }

    protected void tick(Block b) {
        BlockMenu inv = BlockStorage.getInventory(b);
        CraftingOperation currentOperation = processor.getOperation(b);

        if (currentOperation != null) {
            if (takeCharge(b.getLocation())) {

                if (!currentOperation.isFinished()) {
                    processor.updateProgressBar(inv, 22, currentOperation);
                    currentOperation.addProgress(1);
                } else {
                    inv.replaceExistingItem(22, new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "));

                    for (ItemStack output : currentOperation.getResults()) {
                        inv.pushItem(output.clone(), getOutputSlots());
                    }

                    processor.endOperation(b);

                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            Bukkit.getPluginManager()
                                    .callEvent(new PlayerCraftItemEvent(b, processing.get(b).getInput(), processor.getOperation(b).getResults()[0]));
                        }

                    }.runTask(ClayTech.getInstance());
                }
            }
        } else {
            MachineRecipe next = findNextRecipe(inv);

            if (next != null) {
                processor.startOperation(b, new CraftingOperation(next));
            }
        }
    }

    /**
     * This method will remove charge from a location if it is chargeable.
     *
     * @author TheBusyBiscuit
     * @param l
     *            location to try to remove charge from
     * @return Whether charge was taken if its chargeable
     */
    protected boolean takeCharge(Location l) {
        Validate.notNull(l, "Can't attempt to take charge from a null location!");

        if (isChargeable()) {
            int charge = getCharge(l);

            if (charge < getEnergyConsumption()) {
                return false;
            }

            setCharge(l, charge - getEnergyConsumption());
            return true;
        } else {
            return true;
        }
    }

    private MachineRecipe findNextRecipe(BlockMenu inv) {
        MachineRecipe ret = null;
        Map<Integer, Integer> found = new HashMap<>();
        int i;
        for (MachineRecipe recipe : recipes) {
            i = 0;
            for (ItemStack input : recipe.getInput()) {
                if (SlimefunUtils.isItemSimilar(inv.getItemInSlot(inputSlots[i]), input, true)) {

                    if (input != null) {
                        found.put(inputSlots[i], input.getAmount());
                    }
                }
                if (inv.getItemInSlot(inputSlots[i]) == input && input == null) {
                    found.put(i, 0);
                }
                if (i < 8) {
                    i++;
                } else
                    i = 0;
            }
            if (found.size() == recipe.getInput().length) {
                ret = recipe;
                break;
            } else
                found.clear();
        }

        return ret;
    }
}
