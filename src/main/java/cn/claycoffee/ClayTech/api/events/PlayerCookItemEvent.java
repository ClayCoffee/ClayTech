package cn.claycoffee.ClayTech.api.events;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a player cooked a item. 当一个玩家使用锅煮物品成功的时候触发.
 */
public class PlayerCookItemEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final ItemStack[] recipe;
    private final ItemStack item;
    private final Block machine;

    public PlayerCookItemEvent(Block machine, ItemStack[] recipe, ItemStack item) {
        this.recipe = recipe;
        this.item = item;
        this.machine = machine;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * @return the recipe.合成配方
     */
    public ItemStack[] getRecipe() {
        return recipe;
    }

    /**
     * @return the item just crafted.刚刚被合成的物品
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * @return the machine crafted the item.合成物品的机器.
     */
    public Block getMachine() {
        return machine;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
