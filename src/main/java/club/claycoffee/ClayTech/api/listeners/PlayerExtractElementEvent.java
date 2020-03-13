package club.claycoffee.ClayTech.api.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a player extracted a item. 
 */
public class PlayerExtractElementEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private Block machine;
	private ItemStack[] Recipe;
	private ItemStack Element;

	public PlayerExtractElementEvent(Block machine, ItemStack[] Recipe, ItemStack Element) {
		this.Recipe = Recipe;
		this.Element = Element;
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
	 * @return the element just extracted.
	 */
	public ItemStack getElement() {
		return Element;
	}

	/**
	 *
	 * @return the machine extracted the element.
	 */
	public Block getMachine() {
		return machine;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}
