package club.claycoffee.ClayTech.items;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.Recipes;
import club.claycoffee.ClayTech.TRecipe;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.NamespacedKey;

public class MachineMakingBasic {
	public MachineMakingBasic() {

		Slimefunutils.registerItem(Defines.C_MATERIALS, "BLISTERING_CORE",
				Defines.BLISTERING_CORE, "notresearch", 10, TRecipe.CLAY_CRAFTING_TABLE, Recipes.BLISTERING_CORE, false);

		Research before_element = new Research(new NamespacedKey(ClayTech.plugin, "CLAYTECH_BEFORE_ELEMENT"), 9917,
				Lang.readResearchesText("CLAYTECH_BEFORE_ELEMENTS"), 50);

		before_element.addItems(new SlimefunItem[] { SlimefunItem.getByItem(Defines.BLISTERING_CORE) });
		before_element.register();
	}
	
	
}
