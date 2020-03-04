package club.claycoffee.ClayTech.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.TRecipe;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

public class Armors {
	public Armors() {
		ItemStack[] recipea = { Defines.ADVANCED_SLOWNESS_CORE, Defines.ADVANCED_SLOWNESS_CORE,
				Defines.ADVANCED_SLOWNESS_CORE, Defines.ADVANCED_SLOWNESS_CORE, new ItemStack(Material.IRON_BOOTS),
				Defines.ADVANCED_SLOWNESS_CORE, Defines.ADVANCED_SLOWNESS_CORE, Defines.ADVANCED_SLOWNESS_CORE,
				Defines.ADVANCED_SLOWNESS_CORE };

		Slimefunutils.registerItem(Defines.C_ARMORS, "ANTI_SLOWNESS_BOOTS", Defines.ANTI_SLOWNESS_BOOTS, "notresearch",
				10, TRecipe.CLAY_CRAFTING_TABLE, recipea, false);

		Research basic = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_ARMORS"), 9908, "基础效果装备", 50);
		basic.addItems(SlimefunItem.getByItem(Defines.ANTI_SLOWNESS_BOOTS));
		basic.register();
	}
}
