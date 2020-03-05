package club.claycoffee.ClayTech.listener;




import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;

import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.utils.Affect;
import club.claycoffee.ClayTech.utils.Food;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Utils;

public class ClayTechListener implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST,ignoreCancelled=true)
	public void BlockBreakEvent(BlockBreakEvent e) {
		if(!e.isCancelled()) {
			if(e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
				Food.CheckDestroy(e.getPlayer(),e.getBlock(),new ItemStack(Material.OAK_LEAVES),Defines.LEMON,new ItemStack(Material.SHEARS),10,e);
				try {
					// 这里放其他事件
					Food.CheckDestroy(e.getPlayer(),e.getBlock(),new ItemStack(Material.GRASS),Defines.DIRTY_TEA,new ItemStack(Material.SHEARS),10,e);
					Food.CheckDestroy(e.getPlayer(),e.getBlock(),new ItemStack(Material.WHEAT),Defines.FLOUR,new ItemStack(Material.SHEARS),15,20,e);
					Food.CheckDestroy(e.getPlayer(),e.getBlock(),new ItemStack(Material.POTATOES),Defines.STARCH,new ItemStack(Material.SHEARS),15,20,e);
				}catch(NullPointerException err) {}
			}
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerInteractEvent(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR) {
			Player p = e.getPlayer();
			if(e.hasItem()) {
				Food.DrinkCheck(p, e.getItem(), Defines.CLAY_COFFEE, 5, new PotionEffect[] {new PotionEffect(PotionEffectType.NIGHT_VISION,3600,1)});
				try {
					// 这里放其他食物/饮料8!!
					Food.DrinkCheck(p, e.getItem(), Defines.LEMON_POWDER_DRINK, 6, new PotionEffect[] {new PotionEffect(PotionEffectType.CONFUSION,200,3)});
					Food.DrinkCheck(p, e.getItem(), Defines.TEA_DRINK, 6, new PotionEffect[] {new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,600,2)});
					Food.DrinkCheck(p, e.getItem(), Defines.LEMON_TEA_DRINK, 12, new PotionEffect[] {new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,1200,2)});
					Food.FoodCheck(p, e.getItem(), Defines.CHICKEN_FOOT, 8);
					Food.FoodCheck(p, e.getItem(), Defines.RAW_BREAD, 4);
					Food.FoodCheck(p, e.getItem(), Defines.RAW_VEGETABLE, 1);
					Food.FoodCheck(p, e.getItem(), Defines.LEMON, 1,new PotionEffect[] {new PotionEffect(PotionEffectType.CONFUSION,200,3)});
					Food.FoodCheck(p, e.getItem(), Defines.SPICY_CHICKEN_BURGER, 15,new PotionEffect[] {new PotionEffect(PotionEffectType.getById(5),400,1)});
					Food.FoodCheck(p, e.getItem(), Defines.BABA_BURGER, -15,new PotionEffect[] {new PotionEffect(PotionEffectType.POISON,3600,5)});
					Food.FoodCheck(p, e.getItem(), Defines.SNAIL_BAD, -20,new PotionEffect[] {new PotionEffect(PotionEffectType.POISON,8000,9)});
					Food.FoodCheck(p, e.getItem(), Defines.CHOCOLATE, 15,new PotionEffect[] {new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,600,2)});
					Food.FoodCheck(p, e.getItem(), Defines.SNAIL_FOOD, 12,new PotionEffect[] {new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,600,2)});
					Food.FoodCheck(p, e.getItem(), Defines.HONEY_SWEET, 8);
				}catch(NullPointerException err) {}
				Food.WashCheck(p, e.getItem(), Defines.DIRTY_DRINK_BOTTLE, Defines.DRINK_BOTTLE);
				try {
					// 这里放其他清理Event!!
					Food.WashCheck(p, e.getItem(), Defines.DIRTY_TEA, Defines.RAW_TEA);
				}catch(NullPointerException err) {}
			}
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerFishEvent(PlayerFishEvent e) {
		if(e.getState()==State.CAUGHT_FISH) {
			Food.FishItemCheck(e,1,10,Defines.SNAIL_HEALTHY);
			try {
				// 这里放其他食物/饮料8!!
				Food.FishItemCheck(e,11,20,Defines.SNAIL_BAD);
			}catch(NullPointerException err) {}
			
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
			}
			catch(NullPointerException err) {
				
			}
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void BlockPlaceEvent(BlockPlaceEvent e) {
		if(Utils.ExitsInList(Lang.readGeneralText("CantPlaceLore"),Utils.getLore(e.getItemInHand()))){
			e.setBuild(false);
			e.setCancelled(false);
			e.getPlayer().sendMessage(Lang.readGeneralText("CantPlace"));
		}
		
	}
}
