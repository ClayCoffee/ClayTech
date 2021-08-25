package cn.claycoffee.ClayTech.implementation.abstractMachines;

import cn.claycoffee.ClayTech.utils.Lang;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AExtracter extends SlimefunItem implements InventoryBlock, EnergyNetComponent, MachineProcessHolder<CraftingOperation> {
    public final static int[] inputSlots = new int[]{20};
    public final static int[] outputSlots = new int[]{24};
    public static final Map<Block, MachineRecipe> processing = new HashMap<>();
    public static final Map<Block, Integer> progress = new HashMap<>();
    private static final int[] BORDER = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49,
            50, 51, 52, 53, 13};
    private static final int[] BORDER_A = {10, 11, 12, 19, 21, 28, 29, 30, 14, 15, 16, 23, 25, 32, 33, 34};
    private static final int[] BORDER_B = {37, 38, 39, 41, 42, 43};
    private static final ItemStack BORDER_ITEM = new CustomItem(Material.BLACK_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final ItemStack BORDERA_ITEM = new CustomItem(Material.LIME_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final ItemStack BORDERB_ITEM = new CustomItem(Material.MAGENTA_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    protected final List<MachineRecipe> recipes = new ArrayList<>();
    private final MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this);

    public AExtracter(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
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

    public abstract void registerDefaultRecipes();

    @SuppressWarnings("deprecation")
    public void constructMenu(BlockMenuPreset preset) {
        preset.addItem(5, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
        for (int eachID : BORDER) {
            preset.addItem(eachID, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        for (int eachID : BORDER_A) {
            preset.addItem(eachID, BORDERA_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        for (int eachID : BORDER_B) {
            preset.addItem(eachID, BORDERB_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        preset.addItem(22, new CustomItem(Material.PINK_STAINED_GLASS_PANE, " "),
                ChestMenuUtils.getEmptyClickHandler());
        preset.addItem(5, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
        preset.addItem(31,
                new CustomItem(SlimefunPlugin.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_14)
                        ? Material.OAK_SIGN
                        : Material.LEGACY_SIGN, Lang.readMachinesText("ELEMENT_UNIT_DOWN")),
                ChestMenuUtils.getEmptyClickHandler());

        for (int i : getOutputSlots()) {
            preset.addMenuClickHandler(i, new AdvancedMenuClickHandler() {

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
        registerRecipe(new MachineRecipe(seconds, input, output));
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                AExtracter.this.tick(b);
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
                    inv.replaceExistingItem(22, new CustomItem(Material.PINK_STAINED_GLASS_PANE, " "));

                    for (ItemStack output : currentOperation.getResults()) {
                        inv.pushItem(output.clone(), getOutputSlots());
                    }

                    processor.endOperation(b);
                }
            }
        } else {
            MachineRecipe next = findNextRecipe(inv);

            if (next != null) {
                processor.startOperation(b, new CraftingOperation(next));
            }
        }
    }

    @Override
    public MachineProcessor<CraftingOperation> getMachineProcessor() {
        return processor;
    }

    /**
     * This method will remove charge from a location if it is chargeable.
     *
     * @param l location to try to remove charge from
     * @return Whether charge was taken if its chargeable
     * @author TheBusyBiscuit
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

    protected MachineRecipe findNextRecipe(BlockMenu inv) {
        MachineRecipe r = null;
        Map<Integer, Integer> found = new HashMap<>();
        for (MachineRecipe recipe : recipes) {
            ItemStack input = recipe.getInput()[0];
            if (SlimefunUtils.isItemSimilar(inv.getItemInSlot(inputSlots[0]), input, true)) {
                if (input != null) {
                    found.put(inputSlots[0], input.getAmount());
                }
            }
            if (found.size() == recipe.getInput().length) {
                r = recipe;
                break;
            } else {
                found.clear();
            }
        }

        return r;
    }

}
