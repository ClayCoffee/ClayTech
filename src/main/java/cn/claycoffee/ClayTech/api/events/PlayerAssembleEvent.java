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
    private Block machine;
    private ItemStack[] Recipe;
    private ItemStack AssembledItem;

    public PlayerAssembleEvent(Block machine, ItemStack[] Recipe, ItemStack AssembledItem) {
        this.Recipe = Recipe;
        this.AssembledItem = AssembledItem;
        this.machine = machine;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public ItemStack[] getRecipe() {
        return Recipe;
    }

    public ItemStack getAssembledItem() {
        return AssembledItem;
    }

    public Block getMachine() {
        return machine;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
