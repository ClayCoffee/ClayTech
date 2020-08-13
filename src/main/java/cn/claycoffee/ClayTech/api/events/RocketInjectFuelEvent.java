package cn.claycoffee.ClayTech.api.events;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a rocket injected fuel.当一个火箭注入燃料的时候触发.
 */
public class RocketInjectFuelEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Block machine;
    private ItemStack Fuel;
    private ItemStack Rocket;

    public RocketInjectFuelEvent(Block machine, ItemStack Fuel, ItemStack Rocket) {
        this.Fuel = Fuel;
        this.Rocket = Rocket;
        this.machine = machine;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * @return the fuel.燃料.
     */
    public ItemStack getFuel() {
        return Fuel;
    }

    /**
     * @return the rocket.火箭
     */
    public ItemStack getRocket() {
        return Rocket;
    }

    /**
     * @return the injector.注入的机器.
     */
    public Block getMachine() {
        return machine;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
