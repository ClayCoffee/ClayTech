package club.claycoffee.ClayTech.items;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.TRecipe;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class MachineMakingBasic {
	public MachineMakingBasic() {
		ItemStack[] recipea = { SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BLISTERING_INGOT_3,
				SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BLISTERING_INGOT_3,
				SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BLISTERING_INGOT_3,
				SlimefunItems.BLISTERING_INGOT_3 };

		Slimefunutils.registerItem(Defines.C_MATERIALS, "BLISTERING_CORE",
				Defines.BLISTERING_CORE, "notresearch", 10, TRecipe.CLAY_CRAFTING_TABLE, recipea, false);

		Research before_element = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_BEFORE_ELEMENT"), 9917,
				"元素热身", 50);

		before_element.addItems(new SlimefunItem[] { SlimefunItem.getByItem(Defines.BLISTERING_CORE) });
		before_element.register();
	}
}
