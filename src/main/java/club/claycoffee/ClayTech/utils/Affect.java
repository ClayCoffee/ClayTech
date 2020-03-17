package club.claycoffee.ClayTech.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import club.claycoffee.ClayTech.utils.Utils;
import me.mrCookieSlime.Slimefun.api.Slimefun;

public class Affect {
	public static void AffectCheck(Player d, Player e) {
		if (d.getInventory().getItemInMainHand() != null) {
			ItemStack HandItem = d.getInventory().getItemInMainHand();
			if (Slimefun.hasUnlocked(d, HandItem, true)) {
				try {
					if (Utils.ExitsInList(Lang.readGeneralText("Blind_5_effect"), Utils.getLore(HandItem))) {
						e.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 4));
					}
					if (Utils.ExitsInList(Lang.readGeneralText("Slowness_5_effect"), Utils.getLore(HandItem))) {
						e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 4));
					}
					if (Utils.ExitsInList(Lang.readGeneralText("Confusion_5_effect"), Utils.getLore(HandItem))) {
						e.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 4));
					}
					if (Utils.ExitsInList(Lang.readGeneralText("Poison_3_effect"), Utils.getLore(HandItem))) {
						e.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40, 2));
					}
				} catch (NullPointerException err2) {
				}
			}
		}
		if (e.getInventory().getBoots() != null) {
			if (Slimefun.hasUnlocked(e, e.getInventory().getBoots(), true)) {
				try {
					if (Utils.ExitsInList(Lang.readGeneralText("Anti_Slowness_3_effect"),
							Utils.getLore(e.getInventory().getBoots()))
							&& Slimefun.hasUnlocked(e, e.getInventory().getBoots(), true)) {
						d.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2));
					}
				} catch (NullPointerException err2) {
				}
			}
		}
	}
}
