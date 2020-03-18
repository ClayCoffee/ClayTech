package club.claycoffee.ClayTech.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import club.claycoffee.ClayTech.utils.Affect;
import club.claycoffee.ClayTech.utils.Utils;

public class WeaponListener implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		if (e.getDamager().getType() == EntityType.ARROW) {
			try {
				Player d = (Player) ((Projectile) e.getDamager()).getShooter();
				Player p = (Player) e.getEntity();
				Affect.AffectCheck(d, p);
			} catch (Exception err) {
			}
		} else {
			if (e.getDamager().getType() == EntityType.PLAYER && e.getEntity().getType() == EntityType.PLAYER) {
				Player d = (Player) e.getDamager();
				Player p = (Player) e.getEntity();
				Affect.AffectCheck(d, p);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerInteractEntityEvent(PlayerInteractEntityEvent e) {
		if (e.getRightClicked().getType() == EntityType.PLAYER) {
			Player d = e.getPlayer();
			try {
				if (Utils.ExitsInList("§7钩子武器", Utils.getLore(d.getInventory().getItemInMainHand()))) {
					Player p = (Player) e.getRightClicked();
					Affect.AffectCheck(d, p);
				}
			} catch (Exception err) {

			}
		}
	}
}
