package cn.claycoffee.ClayTech.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a player ate a food. 当一个玩家吃粘土科技中的食物的时候触发.
 */
public class PlayerEatEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final ItemStack food;

    public PlayerEatEvent(Player p, ItemStack food) {
        this.player = p;
        this.food = food;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * @return the player who ate the food.吃食物的玩家
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return the food eaten by the player.被吃的食物
     */
    public ItemStack getFood() {
        return food;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
