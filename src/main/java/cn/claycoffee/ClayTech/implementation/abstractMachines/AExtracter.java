package cn.claycoffee.ClayTech.implementation.abstractMachines;

import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.utils.Lang;
import cn.claycoffee.ClayTech.utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
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
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AExtracter extends SlimefunItem implements InventoryBlock, EnergyNetComponent {
    public final static int[] inputslots = new int[]{20};
    public final static int[] outputslots = new int[]{24};
    private static final int[] BORDER = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49,
            50, 51, 52, 53, 13};
    private static final int[] BORDER_A = {10, 11, 12, 19, 21, 28, 29, 30, 14, 15, 16, 23, 25, 32, 33, 34};
    private static final int[] BORDER_B = {37, 38, 39, 41, 42, 43};
    private static final ItemStack BORDER_ITEM = Utils.newItemD(Material.BLACK_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final ItemStack BORDERA_ITEM = Utils.newItemD(Material.LIME_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final ItemStack BORDERB_ITEM = Utils.newItemD(Material.MAGENTA_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    public static Map<Block, MachineRecipe> processing = new HashMap<>();
    public static Map<Block, Integer> progress = new HashMap<>();
    protected final List<MachineRecipe> recipes = new ArrayList<>();
    SlimefunItemStack items;

    public AExtracter(Category category, SlimefunItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {

        super(category, item, recipeType, recipe);

        createPreset(this, getInventoryTitle(), this::SetupMenu);

        registerBlockHandler(id, (p, b, tool, reason) -> {
            BlockMenu inv = BlockStorage.getInventory(b);
            if (inv != null) {
                for (int slot : getInputSlots()) {
                    if (inv.getItemInSlot(slot) != null) {
                        b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                        inv.replaceExistingItem(slot, null);
                    }
                }

                for (int slot : getOutputSlots()) {
                    if (inv.getItemInSlot(slot) != null) {
                        b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                        inv.replaceExistingItem(slot, null);
                    }
                }

                if (inv.getItemInSlot(40) != null) {
                    b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(40));
                    inv.replaceExistingItem(40, null);
                }
            }

            progress.remove(b);
            processing.remove(b);
            return true;
        });

        this.registerDefaultRecipes();
    }

    public int[] getInputSlots() {
        return inputslots;
    }

    @Override
    public int[] getOutputSlots() {
        return outputslots;
    }

    public abstract String getInventoryTitle();

    public abstract ItemStack getProgressBar();

    public abstract int getEnergyConsumption();

    public abstract int getSpeed();

    public abstract String getMachineIdentifier();

    public abstract void registerDefaultRecipes();

    @SuppressWarnings("deprecation")
    public void SetupMenu(BlockMenuPreset Preset) {
        Preset.addItem(5, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
        for (int eachID : BORDER) {
            Preset.addItem(eachID, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        for (int eachID : BORDER_A) {
            Preset.addItem(eachID, BORDERA_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        for (int eachID : BORDER_B) {
            Preset.addItem(eachID, BORDERB_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        Preset.addItem(22, Utils.addLore(Utils.newItem(Material.PINK_STAINED_GLASS_PANE), " "),
                ChestMenuUtils.getEmptyClickHandler());
        Preset.addItem(5, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
        Preset.addItem(31,
                Utils.newItemD(SlimefunPlugin.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_14)
                        ? Material.OAK_SIGN
                        : Material.LEGACY_SIGN, Lang.readMachinesText("ELEMENT_UNIT_DOWN")),
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
        // 机器正在处理
        if (isProcessing(b)) {
            // 剩余时间
            int timeleft = progress.get(b);

            if (timeleft > 0) {
                // 还在处理
                ChestMenuUtils.updateProgressbar(inv, 22, timeleft, processing.get(b).getTicks(), getProgressBar());

                if (isChargeable()) {
                    if (getCharge(b.getLocation()) < getEnergyConsumption())
                        return;
                    removeCharge(b.getLocation(), getEnergyConsumption());
                    progress.put(b, timeleft - 1);
                } else
                    progress.put(b, timeleft - 1);
            } else {
                // 处理结束
                inv.replaceExistingItem(22, Utils.addLore(Utils.newItem(Material.PINK_STAINED_GLASS_PANE), " "));

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
                if (SlimefunUtils.isItemSimilar(inv.getItemInSlot(inputslots[0]), input, true)) {
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
                if (isChargeable()) {
                    if (getCharge(b.getLocation()) < getEnergyConsumption())
                        return;
                    removeCharge(b.getLocation(), getEnergyConsumption());
                }
                if (!SlimefunUtils.isItemSimilar(inv.getItemInSlot(40), ClayTechItems.ELEMENT_UNIT, true))
                    return;
                if (inv.getItemInSlot(outputslots[0]) != null) {
                    ItemStack is = inv.getItemInSlot(outputslots[0]);
                    if (is.getMaxStackSize() == is.getAmount())
                        return;
                }
                for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
                    if (entry.getValue() > 0) {
                        inv.consumeItem(entry.getKey(), entry.getValue());
                        inv.consumeItem(40, 1);
                    }

                }

                processing.put(b, r);
                progress.put(b, r.getTicks());
            }
        }
    }

}
