package cn.claycoffee.ClayTech.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a player used a ClayTech item. 当一个玩家使用粘土科技中的物品时触发.
 */
public class PlayerUseItemEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private ItemStack item;

    public PlayerUseItemEvent(Player player, ItemStack item) {
        this.player = player;
        this.item = item;
    }

    /**
     * @return the player.玩家
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return the item.物品
     */
    public ItemStack getItem() {
        return item;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public HandlerList getHandlerList() {
        return handlers;
    }
}
