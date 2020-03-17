package club.claycoffee.ClayTech.listener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Projectile;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.api.listeners.PlayerUseItemEvent;
import club.claycoffee.ClayTech.utils.Affect;
import club.claycoffee.ClayTech.utils.ClayItem;
import club.claycoffee.ClayTech.utils.Food;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Utils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public class ClayTechListener implements Listener {
	Player p;

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void BlockBreakEvent(BlockBreakEvent e) {
		if (!e.isCancelled()) {
			if (e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
				Food.CheckDestroy(e.getPlayer(), e.getBlock(), new ItemStack(Material.OAK_LEAVES),
						ClayTechItems.CLAY_LEMON, new ItemStack(Material.SHEARS), 10, e);
				try {
					// 这里放其他事件
					Food.CheckDestroy(e.getPlayer(), e.getBlock(), new ItemStack(Material.GRASS),
							ClayTechItems.DIRTY_TEA, new ItemStack(Material.SHEARS), 10, e);
					Food.CheckDestroy(e.getPlayer(), e.getBlock(), new ItemStack(Material.WHEAT), ClayTechItems.FLOUR,
							new ItemStack(Material.SHEARS), 15, 20, e);
					Food.CheckDestroy(e.getPlayer(), e.getBlock(), new ItemStack(Material.POTATOES),
							ClayTechItems.STARCH, new ItemStack(Material.SHEARS), 15, 20, e);
					Food.CheckDestroy(e.getPlayer(), e.getBlock(), new ItemStack(Material.POTATOES),
							ClayTechItems.CLAY_SWEET_POTATO, new ItemStack(Material.SHEARS), 25, 30, e);
				} catch (NullPointerException err) {
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerInteractEvent(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR) {
			if (Utils.ExitsInList(Lang.readGeneralText("CantEat"), Utils.getLore(e.getItem()))) {
				e.getPlayer().sendMessage(Lang.readGeneralText("CantEatMessage"));
				return;
			}
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
								new FixedMetadataValue(ClayTech.plugin, System.currentTimeMillis() + ""));
						new BukkitRunnable() {
							@Override
							public void run() {
								e.getPlayer().getWorld().spawnEntity(currentLoc, EntityType.PRIMED_TNT);
								Block center = currentLoc.add(0, -1, 0).getBlock();
								center.setMetadata("isExplosionCreater", new FixedMetadataValue(ClayTech.plugin, true));
								p = e.getPlayer();
								return;
							}

						}.runTaskLater(ClayTech.plugin, 100);
					} else {
						e.getPlayer().sendMessage(Lang.readGeneralText("TNT_EXPLOSION_CREATER_NO_TNT"));
						return;
					}

				} else {
					e.getPlayer().sendMessage(Lang.readGeneralText("TNT_EXPLOSION_CREATER_CD"));
					return;
				}

			}
			Player p = e.getPlayer();
			if (e.hasItem()) {
				Food.DrinkCheck(p, e.getItem(), ClayTechItems.CLAY_COFFEE, 5,
						new PotionEffect[] { new PotionEffect(PotionEffectType.NIGHT_VISION, 3600, 1) });
				try {
					// 这里放其他食物/饮料8!!
					Food.DrinkCheck(p, e.getItem(), ClayTechItems.LEMON_POWDER_DRINK, 6,
							new PotionEffect[] { new PotionEffect(PotionEffectType.CONFUSION, 200, 3) });
					Food.DrinkCheck(p, e.getItem(), ClayTechItems.TEA_DRINK, 6,
							new PotionEffect[] { new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 2) });
					Food.DrinkCheck(p, e.getItem(), ClayTechItems.LEMON_TEA_DRINK, 12,
							new PotionEffect[] { new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200, 2) });
					Food.FoodCheck(p, e.getItem(), ClayTechItems.CHICKEN_FOOT, 8);
					Food.FoodCheck(p, e.getItem(), ClayTechItems.RAW_BREAD, 4);
					Food.FoodCheck(p, e.getItem(), ClayTechItems.RAW_VEGETABLE, 1);
					Food.FoodCheck(p, e.getItem(), ClayTechItems.CLAY_LEMON, 1,
							new PotionEffect[] { new PotionEffect(PotionEffectType.CONFUSION, 200, 3) });
					Food.FoodCheck(p, e.getItem(), ClayTechItems.SPICY_CHICKEN_BURGER, 15,
							new PotionEffect[] { new PotionEffect(PotionEffectType.getById(5), 400, 1) });
					Food.FoodCheck(p, e.getItem(), ClayTechItems.BABA_BURGER, -15,
							new PotionEffect[] { new PotionEffect(PotionEffectType.POISON, 3600, 5) });
					Food.FoodCheck(p, e.getItem(), ClayTechItems.SNAIL_BAD, -20,
							new PotionEffect[] { new PotionEffect(PotionEffectType.POISON, 8000, 9) });
					Food.FoodCheck(p, e.getItem(), ClayTechItems.CHOCOLATE, 15,
							new PotionEffect[] { new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 2) });
					Food.FoodCheck(p, e.getItem(), ClayTechItems.SNAIL_FOOD, 12,
							new PotionEffect[] { new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 2) });
					Food.FoodCheck(p, e.getItem(), ClayTechItems.HONEY_SWEET, 8);
					Food.FoodCheck(p, e.getItem(), ClayTechItems.COOKED_SWEET_POTATO, 6);
				} catch (NullPointerException err) {
				}
				Food.WashCheck(p, e.getItem(), ClayTechItems.DIRTY_DRINK_BOTTLE, ClayTechItems.DRINK_BOTTLE);
				try {
					// 这里放其他清理Event!!
					Food.WashCheck(p, e.getItem(), ClayTechItems.DIRTY_TEA, ClayTechItems.RAW_TEA);
				} catch (NullPointerException err) {
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
				if (eachv.getOwningPlugin().equals(ClayTech.plugin)) {
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

	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerFishEvent(PlayerFishEvent e) {
		if (e.getState() == State.CAUGHT_FISH) {
			Food.FishItemCheck(e, 1, 10, ClayTechItems.SNAIL_HEALTHY);
			try {
				// 这里放其他食物/饮料8!!
				Food.FishItemCheck(e, 11, 20, ClayTechItems.SNAIL_BAD);
			} catch (NullPointerException err) {
			}

		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		if (e.getDamager().getType() == EntityType.ARROW) {
			try {
				Player d = (Player) ((Projectile) e.getDamager()).getShooter();
				Player p = (Player) e.getEntity();
				Affect.AffectCheck(d, p);
			} catch (ClassCastException err) {
			}
		} else {
			if (e.getDamager().getType() == EntityType.PLAYER && e.getEntity().getType() == EntityType.PLAYER) {
				Player d = (Player) e.getDamager();
				Player p = (Player) e.getEntity();
				Affect.AffectCheck(d, p);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerInteractEntityEvent(PlayerInteractEntityEvent e) {
		if (e.getRightClicked().getType() == EntityType.PLAYER) {
			Player d = e.getPlayer();
			try {
				if (Utils.ExitsInList("§7钩子武器", Utils.getLore(d.getItemInHand()))) {
					Player p = (Player) e.getRightClicked();
					Affect.AffectCheck(d, p);
				}
			} catch (NullPointerException err) {

			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void BlockPlaceEvent(BlockPlaceEvent e) {
		if (Utils.ExitsInList(Lang.readGeneralText("CantPlaceLore"), Utils.getLore(e.getItemInHand()))) {
			e.setBuild(false);
			e.setCancelled(false);
			e.getPlayer().sendMessage(Lang.readGeneralText("CantPlace"));
		}

	}

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
						ve.setMaxSpeed(0.4d * new Integer(ClayTech.highrailspeed).doubleValue());
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
