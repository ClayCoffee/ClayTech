package cn.claycoffee.ClayTech.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a player washed a item. 当一个玩家使用水桶清洗某个物品成功的时候触发.
 */
public class PlayerWashEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player eatPlayer;
    private final ItemStack dirty;
    private final ItemStack clean;

    public PlayerWashEvent(Player p, ItemStack dirty, ItemStack clean) {
        this.eatPlayer = p;
        this.dirty = dirty;
        this.clean = clean;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * @return the player who washed the item.洗物品的玩家
     */
    public Player getPlayer() {
        return eatPlayer;
    }

    /**
     * @return the item just washed.已经被清洗的脏物品
     */
    public ItemStack getItem() {
        return dirty;
    }

    /**
     * @return the item after washing.清洗后的干净物品
     */
    public ItemStack getClean() {
        return clean;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
