package club.claycoffee.ClayTech.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.Machines.ANewContainer;
import club.claycoffee.ClayTech.utils.Lang;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.LockedCategory;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class FoodChalkingMachine extends ANewContainer {

	public FoodChalkingMachine(LockedCategory category, SlimefunItemStack item, String id, RecipeType recipeType,
			ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);
	}

	@Override
	public String getInventoryTitle() {
		return Lang.readMachinesText("CLAY_FOOD_CHALKING_MACHINE");
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.REDSTONE_TORCH);
	}

	@Override
	public int getEnergyConsumption() {
		return 32;
	}

	@Override
	public int getCapacity() {
		return 512;
	}

	@Override
	public int getSpeed() {
		return 1;
	}

	@Override
	public String getMachineIdentifier() {
		return "CLAY_FOOD_CHALKING_MACHINE";
	}

	@Override
	public void registerDefaultRecipes() {
		this.registerRecipe(8, new ItemStack[] { ClayTechItems.RAW_TEA }, new ItemStack[] { ClayTechItems.TEA_POWDER });
		this.registerRecipe(8, new ItemStack[] { ClayTechItems.LEMON }, new ItemStack[] { ClayTechItems.LEMON_POWDER });
	}
}
