package club.claycoffee.ClayTech;

import org.bukkit.NamespacedKey;

import club.claycoffee.ClayTech.utils.Lang;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class TRecipe {
	public static final RecipeType CLAY_CRAFTING_TABLE = new RecipeType(
			new NamespacedKey(ClayTech.plugin,"CLAY_CRAFTIONG_TABLE"),new SlimefunItemStack("CLAY_CRAFTING_TABLE", Defines.CLAY_CRAFTING_TABLE), "",
			Lang.readMachineRecipesText("CLAY_FUSION_MACHINE"));
	public static final RecipeType CLAY_FOOD_CAULDRON = new RecipeType(
			new NamespacedKey(ClayTech.plugin,"CLAY_FOOD_CAULDRON"),new SlimefunItemStack("CLAY_FOOD_CAULDRON", Defines.CLAY_FOOD_CAULDRON), "",
			Lang.readMachineRecipesText("CLAY_ELETRIC_CAULDRON"));
	public static final RecipeType CLAY_FOOD_CHALKING_MACHINE = new RecipeType(
			new NamespacedKey(ClayTech.plugin,"CLAY_FOOD_CHALKING_MACHINE"),new SlimefunItemStack("CLAY_FOOD_CHALKING_MACHINE", Defines.CLAY_FOOD_CHALKING_MACHINE), "",
			Lang.readMachineRecipesText("CLAY_FOOD_CHALKING_MACHINE"));
	public static final RecipeType CLAY_ELEMENT_EXTRACTER = new RecipeType(
			new NamespacedKey(ClayTech.plugin,"CLAY_ELEMENT_EXTRACTER"),new SlimefunItemStack("CLAY_ELEMENT_EXTRACTER", Defines.CLAY_ELEMENT_EXTRACTER), "",
			Lang.readMachineRecipesText("CLAY_ELEMENT_EXTRACTER"));
}
