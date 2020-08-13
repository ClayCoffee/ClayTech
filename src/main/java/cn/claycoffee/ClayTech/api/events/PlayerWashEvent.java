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
    private Player eatPlayer;
    private ItemStack Washthing;
    private ItemStack CleanedItem;

    public PlayerWashEvent(Player p, ItemStack ClayDirtyStack, ItemStack CleanedItem) {
        this.eatPlayer = p;
        this.Washthing = ClayDirtyStack;
        this.CleanedItem = CleanedItem;
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
        return Washthing;
    }

    /**
     * @return the item after washing.清洗后的干净物品
     */
    public ItemStack getCleanedItem() {
        return CleanedItem;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
