package club.claycoffee.ClayTech.api.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a player extracted a item.
 * 当一个玩家使用元素提取器提取元素成功的时候触发.
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
	 * @return 合成配方
	 */
	public ItemStack[] getRecipe() {
		return Recipe;
	}

	/**
	 *
	 * @return the element just extracted.
	 * @return 刚刚提取成功的元素
	 */
	public ItemStack getElement() {
		return Element;
	}

	/**
	 *
	 * @return the machine extracted the element.
	 * @return 提取元素的机器
	 */
	public Block getMachine() {
		return machine;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}
