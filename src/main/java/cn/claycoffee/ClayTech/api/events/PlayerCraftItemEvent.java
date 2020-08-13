package cn.claycoffee.ClayTech.api.events;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a player crafted a item. 当一个玩家使用粘土融合器合成物品成功的时候触发.
 */
public class PlayerCraftItemEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Block machine;
    private ItemStack[] Recipe;
    private ItemStack CraftedItem;

    public PlayerCraftItemEvent(Block machine, ItemStack[] Recipe, ItemStack CraftedItem) {
        this.Recipe = Recipe;
        this.CraftedItem = CraftedItem;
        this.machine = machine;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * @return the recipe.合成配方
     */
    public ItemStack[] getRecipe() {
        return Recipe;
    }

    /**
     * @return the item just crafted.刚刚被合成的物品
     */
    public ItemStack getCraftedItem() {
        return CraftedItem;
    }

    /**
     * @return the machine crafted the item.合成物品的机器
     */
    public Block getMachine() {
        return machine;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
