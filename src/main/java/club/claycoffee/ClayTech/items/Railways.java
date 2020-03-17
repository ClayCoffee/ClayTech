package club.claycoffee.ClayTech.items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.Recipes;
import club.claycoffee.ClayTech.TRecipe;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class Railways {
	public Railways() {
		Slimefunutils.registerItem(ClayTechItems.C_OTHER, "CLAY_HIGHSPEED_RAILWAY", ClayTechItems.HIGHSPEED_RAILWAY,
				"notresearch", 10, TRecipe.CLAY_CRAFTING_TABLE, Recipes.HIGHSPEED_RAILWAY, false);
		ItemStack elem8 = ClayTechItems.ELECTRIC_MOTOR_8;
		elem8.setAmount(8);
		SlimefunItemStack motor_8 = new SlimefunItemStack("ELECTRIC_MOTOR_8", elem8);
		SlimefunItem motor_8_i = new SlimefunItem(ClayTechItems.C_OTHER, motor_8, TRecipe.CLAY_CRAFTING_TABLE,
				Recipes.ELECTRIC_MOTOR_8);
		motor_8_i.register(ClayTech.plugin);
		Research railway_basic = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_RAILWAY_BASIC"), 9920,
				Lang.readResearchesText("CLAYTECH_RAILWAY_I"), 40);
		railway_basic.addItems(SlimefunItem.getByItem(ClayTechItems.HIGHSPEED_RAILWAY),
				SlimefunItem.getByItem(ClayTechItems.ELECTRIC_MOTOR_8));
		railway_basic.register();
	}
}
