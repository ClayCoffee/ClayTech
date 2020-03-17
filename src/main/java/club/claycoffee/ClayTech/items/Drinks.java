package club.claycoffee.ClayTech.items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.TRecipe;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class Drinks {
	public Drinks() {
		ItemStack[] recipea = { null, ClayTechItems.COCOA_BEAN, null, null, ClayTechItems.COCOA_BEAN, null, null,
				ClayTechItems.DRINK_BOTTLE, null };
		ItemStack[] recipeb = { null, ClayTechItems.LEMON_POWDER, null, null, ClayTechItems.LEMON_POWDER, null, null,
				ClayTechItems.DRINK_BOTTLE, null };
		ItemStack[] recipec = { null, ClayTechItems.TEA_POWDER, null, null, ClayTechItems.TEA_POWDER, null, null,
				ClayTechItems.DRINK_BOTTLE, null };
		ItemStack[] reciped = { null, ClayTechItems.TEA_POWDER, null, ClayTechItems.LEMON_POWDER,
				ClayTechItems.TEA_POWDER, ClayTechItems.LEMON_POWDER, null, ClayTechItems.DRINK_BOTTLE, null };

		Slimefunutils.registerItem(ClayTechItems.C_DRINK, "CLAY_COFFEE", ClayTechItems.CLAY_COFFEE, "notresearch", 10,
				TRecipe.CLAY_FOOD_CAULDRON, recipea, false);
		Slimefunutils.registerItem(ClayTechItems.C_DRINK, "LEMON_POWDER_DRINK", ClayTechItems.LEMON_POWDER_DRINK,
				"notresearch", 10, TRecipe.CLAY_FOOD_CAULDRON, recipeb, false);
		Slimefunutils.registerItem(ClayTechItems.C_DRINK, "TEA_DRINK", ClayTechItems.TEA_DRINK, "notresearch", 10,
				TRecipe.CLAY_FOOD_CAULDRON, recipec, false);
		Slimefunutils.registerItem(ClayTechItems.C_DRINK, "LEMON_TEA_DRINK", ClayTechItems.LEMON_TEA_DRINK,
				"notresearch", 10, TRecipe.CLAY_FOOD_CAULDRON, reciped, false);

		Research foodI = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_DRINKBASIC"), 9909,
				Lang.readResearchesText("CLAYTECH_DRINK_I"), 50);
		foodI.addItems(SlimefunItem.getByItem(ClayTechItems.CLAY_COFFEE),
				SlimefunItem.getByItem(ClayTechItems.LEMON_POWDER_DRINK),
				SlimefunItem.getByItem(ClayTechItems.TEA_DRINK), SlimefunItem.getByItem(ClayTechItems.LEMON_TEA_DRINK));
		foodI.register();
	}
}
