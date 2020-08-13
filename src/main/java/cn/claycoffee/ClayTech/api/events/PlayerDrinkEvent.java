package cn.claycoffee.ClayTech.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a player drank a food. 当一个玩家喝粘土科技中的饮料的时候触发.
 */
public class PlayerDrinkEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player eatPlayer;
    private ItemStack drink;

    public PlayerDrinkEvent(Player p, ItemStack ClayFoodStack) {
        this.eatPlayer = p;
        this.drink = ClayFoodStack;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * @return the player who drank the food.喝饮料的玩家
     */
    public Player getPlayer() {
        return eatPlayer;
    }

    /**
     * @return the drink drank by the player.被喝的饮料
     */
    public ItemStack getDrink() {
        return drink;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
