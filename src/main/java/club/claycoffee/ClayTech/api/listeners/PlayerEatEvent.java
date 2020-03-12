package club.claycoffee.ClayTech.api.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PlayerEatEvent extends Event{
	private Player eatPlayer;
	private ItemStack food;
	private static final HandlerList handlers = new HandlerList();
	
	public PlayerEatEvent(Player p, ItemStack ClayFoodStack) {
		this.eatPlayer = p;
		this.food = ClayFoodStack;
	}
	/**
    *
    * @return the player who ate the food.
    */
	public Player getPlayer() {
		return eatPlayer;
	}
	/**
    *
    * @return the food eaten by the player.
    */
	public ItemStack getFood() {
		return food;
	}
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
