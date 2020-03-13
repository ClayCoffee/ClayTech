package club.claycoffee.ClayTech.api.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Called when a ClayTech machine tick. 
 * 当一个粘土科技机器tick的时候触发
 */
public class MachineTickEvent extends Event{
	private static final HandlerList handlers = new HandlerList();
	private Block Machine;
	public MachineTickEvent(Block machine) {
		this.Machine = machine;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	/**
	 *
	 * @return the machine. tick的机器
	 */
	public Block getMachine() {
		return Machine;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}

}
