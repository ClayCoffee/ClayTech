package club.claycoffee.ClayTech.api.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a player washed a item. 
 */
public class PlayerWashEvent extends Event {
	private Player eatPlayer;
	private ItemStack Washthing;
	private ItemStack CleanedItem;
	private static final HandlerList handlers = new HandlerList();

	public PlayerWashEvent(Player p, ItemStack ClayDirtyStack, ItemStack CleanedItem) {
		this.eatPlayer = p;
		this.Washthing = ClayDirtyStack;
		this.CleanedItem = CleanedItem;
	}

	/**
	 *
	 * @return the player who washed the food.
	 */
	public Player getPlayer() {
		return eatPlayer;
	}

	/**
	 *
	 * @return the item just washed.
	 */
	public ItemStack getItem() {
		return Washthing;
	}

	/**
	 *
	 * @return the item after washing.
	 */
	public ItemStack getCleanedItem() {
		return CleanedItem;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
