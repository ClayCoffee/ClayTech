package club.claycoffee.ClayTech.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.api.listeners.PlayerUseItemEvent;
import club.claycoffee.ClayTech.utils.ClayItem;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Utils;

public class ItemUseListener implements Listener {
	private Player p;

	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerInteractEvent(PlayerInteractEvent e) {
		if (e.hasItem()) {
			if (e.getItem().hasItemMeta()) {
				if (e.getItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(Lang.readItemText("TNT_EXPLOSION_CREATER"))) {
					Bukkit.getPluginManager().callEvent(new PlayerUseItemEvent(e.getPlayer(), e.getItem()));
					boolean pass = false;
					String md = Utils.readPlayerMetadataString(e.getPlayer(), "lastUseTNTCreaterTime");
					if (md != null) {
						if (System.currentTimeMillis() >= Long.parseLong(md) + 5000L) {
							pass = true;
						}
					} else {
						pass = true;
					}
					if (pass) {
						if (e.getPlayer().getInventory().containsAtLeast(new ItemStack(Material.TNT), 1)) {
							Location currentLoc = e.getPlayer().getLocation();
							Inventory inv = e.getPlayer().getInventory();
							ItemStack TNT = inv.getItem(inv.first(Material.TNT));
							TNT.setAmount(TNT.getAmount() - 1);
							ItemStack tool = e.getPlayer().getInventory().getItemInMainHand();
							ClayItem.setDurability(tool, ClayItem.getDurability(tool) - 1);
							e.getPlayer().sendMessage(Lang.readGeneralText("TNT_EXPLOSION_CREATER_WAIT"));
							e.getPlayer().setMetadata("lastUseTNTCreaterTime",
									new FixedMetadataValue(ClayTech.getInstance(), System.currentTimeMillis() + ""));
							new BukkitRunnable() {
								@Override
								public void run() {
									e.getPlayer().getWorld().spawnEntity(currentLoc, EntityType.PRIMED_TNT);
									Block center = currentLoc.add(0, -1, 0).getBlock();
									center.setMetadata("isExplosionCreater",
											new FixedMetadataValue(ClayTech.getInstance(), true));
									p = e.getPlayer();
									return;
								}

							}.runTaskLater(ClayTech.getInstance(), 100);
						} else {
							e.getPlayer().sendMessage(Lang.readGeneralText("TNT_EXPLOSION_CREATER_NO_TNT"));
							return;
						}

					} else {
						e.getPlayer().sendMessage(Lang.readGeneralText("TNT_EXPLOSION_CREATER_CD"));
						return;
					}

				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void EntityExplodeEvent(EntityExplodeEvent e) {
		List<Block> blockL = new ArrayList<Block>();
		for (Block b : e.blockList()) {
			blockL.add(b);
		}
		boolean already = false;
		for (Block each : blockL) {
			for (MetadataValue eachv : each.getMetadata("isExplosionCreater")) {
				if (eachv.getOwningPlugin().equals(ClayTech.getInstance())) {
					if (e.isCancelled()) {
						p.sendMessage(Lang.readGeneralText("TNT_EXPLOSION_CREATER_FATAL"));
						return;
					} else {
						if (!already) {
							p.sendMessage(Lang.readGeneralText("TNT_EXPLOSION_CREATER_SUCCESS"));
							already = true;
						}

					}
					if (eachv.asBoolean()) {
						Iterator<Block> b = e.blockList().iterator();
						while (b.hasNext()) {
							Block next = b.next();
							if (next.getType() == Material.CHEST || next.getType() == Material.FURNACE) {
								b.remove();
							}
						}
						break;

					}
				}
			}
		}
	}
}
