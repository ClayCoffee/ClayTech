package club.claycoffee.ClayTech.implementation.items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.ClayTechMachineRecipes;
import club.claycoffee.ClayTech.ClayTechRecipeType;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class Railways {
	public Railways() {
		Slimefunutils.registerItem(ClayTechItems.C_OTHER, "CLAY_HIGHSPEED_RAILWAY", ClayTechItems.HIGHSPEED_RAILWAY,
				"notresearch", 10, ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.HIGHSPEED_RAILWAY,
				false);
		ItemStack elem8 = ClayTechItems.ELECTRIC_MOTOR_8;
		elem8.setAmount(8);
		SlimefunItemStack motor_8 = new SlimefunItemStack("ELECTRIC_MOTOR_8", elem8);
		SlimefunItem motor_8_i = new SlimefunItem(ClayTechItems.C_OTHER, motor_8,
				ClayTechRecipeType.CLAY_CRAFTING_TABLE, ClayTechMachineRecipes.ELECTRIC_MOTOR_8);
		motor_8_i.register(ClayTech.getInstance());
		Research railway_basic = new Research(new NamespacedKey(ClayTech.getInstance(), "CLAYTECH_RAILWAY_BASIC"), 9920,
				Lang.readResearchesText("CLAYTECH_RAILWAY_I"), 40);
		railway_basic.addItems(SlimefunItem.getByItem(ClayTechItems.HIGHSPEED_RAILWAY),
				SlimefunItem.getByItem(ClayTechItems.ELECTRIC_MOTOR_8));
		railway_basic.register();
	}
}
