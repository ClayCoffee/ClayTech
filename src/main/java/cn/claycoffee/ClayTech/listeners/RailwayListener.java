package cn.claycoffee.ClayTech.listeners;

import cn.claycoffee.ClayTech.ClayTech;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;

public class RailwayListener implements Listener {
    @EventHandler
    public void VehicleMoveEvent(VehicleMoveEvent e) {
        if (e.getVehicle() instanceof Minecart) {
            Minecart ve = (Minecart) e.getVehicle();
            World veworld = ve.getWorld();
            Location veloc = ve.getLocation();
            Block rail = veworld.getBlockAt(veloc);
            if (rail.getBlockData().getMaterial() == Material.POWERED_RAIL) {
                if (BlockStorage.checkID(rail) != null) {
                    if (BlockStorage.checkID(rail).equalsIgnoreCase("CLAY_HIGHSPEED_RAILWAY")) {
                        ve.setMaxSpeed(0.4d * new Integer(ClayTech.getHighRailSpeed()).doubleValue());
                    } else {
                        ve.setMaxSpeed(0.4d);
                    }
                } else {
                    ve.setMaxSpeed(0.4d);
                }
            } else {
                ve.setMaxSpeed(0.4d);
            }
        }
    }
}
