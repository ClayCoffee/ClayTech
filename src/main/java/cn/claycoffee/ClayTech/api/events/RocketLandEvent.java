package cn.claycoffee.ClayTech.api.events;

import cn.claycoffee.ClayTech.api.Planet;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a rocket land.在火箭着陆的时候触发.
 */
public class RocketLandEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player onRocketPlayer;
    private Planet fromPlanet;
    private Planet toPlanet;
    private ItemStack rocket;

    public RocketLandEvent(Player p, Planet fromPlanet, Planet toPlanet, ItemStack rocket) {
        this.onRocketPlayer = p;
        this.fromPlanet = fromPlanet;
        this.toPlanet = toPlanet;
        this.rocket = rocket;
    }

    public static HandlerList getHandlerList() {
        return handlers;
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
}
