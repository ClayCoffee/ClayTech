package club.claycoffee.ClayTech.items;

import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.Machines.AElementExtracter;
import club.claycoffee.ClayTech.utils.Lang;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.LockedCategory;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ElementExtracter extends AElementExtracter{
	public ElementExtracter(LockedCategory category, SlimefunItemStack item, String id, RecipeType recipeType,
			ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);
	}

	public String getInventoryTitle() {
		return Lang.readMachinesText("CLAY_ELEMENT_EXTRACTER");
	}

	public ItemStack getProgressBar() {
		return new ItemStack(Material.REDSTONE_TORCH);
	}

	public int getEnergyConsumption() {
		return 64;
	}

	public int getSpeed() {
		return 1;
	}

	public int getCapacity() {
		return 1024;
	}

	public String getMachineIdentifier() {
		return "ELEMENT_EXTRACTER";
	}

	public void registerDefaultRecipes() {
		registerRecipe(10, new ItemStack[] { new ItemStack(Material.DIRT,3)}, new ItemStack[] { Defines.ELEMENT_OXYGEN });
		registerRecipe(10, new ItemStack[] { new ItemStack(Material.COAL,8) }, new ItemStack[] { Defines.ELEMENT_CARBON });
		registerRecipe(10, new ItemStack[] { new ItemStack(Material.SAND,10) }, new ItemStack[] { Defines.ELEMENT_SILICON });
	}
}
