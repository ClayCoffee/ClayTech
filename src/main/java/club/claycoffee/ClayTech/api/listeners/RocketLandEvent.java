package club.claycoffee.ClayTech.api.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.api.Planet;

/**
 * Called when a rocket land.在火箭着陆的时候触发.
 */
public class RocketLandEvent extends Event {
	private Player onRocketPlayer;
	private Planet fromPlanet;
	private Planet toPlanet;
	private ItemStack rocket;
	private static final HandlerList handlers = new HandlerList();

	public RocketLandEvent(Player p, Planet fromPlanet, Planet toPlanet, ItemStack rocket) {
		this.onRocketPlayer = p;
		this.fromPlanet = fromPlanet;
		this.toPlanet = toPlanet;
		this.rocket = rocket;
	}

	public Player getPlayer() {
		return onRocketPlayer;
	}

	public ItemStack getRocket() {
		return rocket;
	}

	public Planet getFromPlanet() {
		return fromPlanet;
	}

	public Planet getToPlanet() {
		return toPlanet;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
