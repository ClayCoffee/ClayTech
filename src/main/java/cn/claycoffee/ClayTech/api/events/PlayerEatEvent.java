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
    private Player eatPlayer;
    private ItemStack food;

    public PlayerEatEvent(Player p, ItemStack ClayFoodStack) {
        this.eatPlayer = p;
        this.food = ClayFoodStack;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * @return the player who ate the food.吃食物的玩家
     */
    public Player getPlayer() {
        return eatPlayer;
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
