package cn.claycoffee.ClayTech.api.events;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a item injected oxygen.当一个物品被注入氧气的时候触发.
 */
public class InjectOxygenEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Block machine;
    private final ItemStack item;

    public InjectOxygenEvent(Block machine, ItemStack item) {
        this.item = item;
        this.machine = machine;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * @return the item.物品
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * @return the injector.注入的机器.
     */
    public Block getMachine() {
        return machine;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
