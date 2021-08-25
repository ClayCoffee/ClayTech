package cn.claycoffee.ClayTech.api.events;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a player assemble a item.当一个玩家组装物品的时候触发.
 */
public class PlayerAssembleEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Block machine;
    private final ItemStack[] recipe;
    private final ItemStack item;

    public PlayerAssembleEvent(Block machine, ItemStack[] recipe, ItemStack item) {
        this.recipe = recipe;
        this.item = item;
        this.machine = machine;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public ItemStack[] getRecipe() {
        return recipe;
    }

    public ItemStack getItem() {
        return item;
    }

    public Block getMachine() {
        return machine;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
