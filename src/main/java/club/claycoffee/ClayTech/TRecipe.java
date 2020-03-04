package club.claycoffee.ClayTech;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

@SuppressWarnings("deprecation")
public class TRecipe {
	public static final RecipeType CLAY_CRAFTING_TABLE = new RecipeType(new SlimefunItemStack("CLAY_CRAFTING_TABLE", Defines.CLAY_CRAFTING_TABLE), "", "&a&o在粘土融合器内合成");
	public static final RecipeType CLAY_FOOD_CAULDRON = new RecipeType(new SlimefunItemStack("CLAY_FOOD_CAULDRON", Defines.CLAY_FOOD_CAULDRON), "", "&a&o在电锅内合成");
	public static final RecipeType CLAY_FOOD_CHALKING_MACHINE = new RecipeType(new SlimefunItemStack("CLAY_FOOD_CHALKING_MACHINE", Defines.CLAY_FOOD_CHALKING_MACHINE), "", "&a&o在食物打粉机内合成");
	public static final RecipeType CLAY_ELEMENT_EXTRACTER = new RecipeType(new SlimefunItemStack("CLAY_ELEMENT_EXTRACTER", Defines.CLAY_ELEMENT_EXTRACTER),  "", "&a&o在元素提取机中提取" );
}
