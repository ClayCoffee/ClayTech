package club.claycoffee.ClayTech.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.utils.Food;

public class FoodEatListener implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerInteractEvent(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR) {
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
}
