package club.claycoffee.ClayTech.api.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a player crafted a item. 
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

	/**
	 *
	 * @return the recipe.
	 */
	public ItemStack[] getRecipe() {
		return Recipe;
	}

	/**
	 *
	 * @return the item just crafted.
	 */
	public ItemStack getCraftedItem() {
		return CraftedItem;
	}

	/**
	 *
	 * @return the machine crafted the item.
	 */
	public Block getMachine() {
		return machine;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}
