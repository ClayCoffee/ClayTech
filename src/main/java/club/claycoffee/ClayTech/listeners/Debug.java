package club.claycoffee.ClayTech.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.api.ClayTechManager;
import club.claycoffee.ClayTech.utils.PlanetUtils;

public class Debug implements Listener {
	@EventHandler
	public void onWantTeleport(AsyncPlayerChatEvent e) {
		if (e.getMessage().equalsIgnoreCase("gomoon")) {
			new BukkitRunnable() {

				@Override
				public void run() {
					ClayTechManager.allowSpaceTeleportOnce(e.getPlayer());
					e.getPlayer().teleport(PlanetUtils.findSafeLocation(Bukkit.getWorld("CMoon")));
				}

			}.runTask(ClayTech.getInstance());
		}
	}
}
