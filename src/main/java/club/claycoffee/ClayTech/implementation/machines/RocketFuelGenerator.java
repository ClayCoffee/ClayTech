package club.claycoffee.ClayTech.implementation.machines;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.ClayTechMachineRecipes;
import club.claycoffee.ClayTech.implementation.abstractMachines.ACraftingTable;
import club.claycoffee.ClayTech.utils.Lang;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class RocketFuelGenerator extends ACraftingTable {

	public RocketFuelGenerator(Category category, SlimefunItemStack item, String id, RecipeType recipeType,
			ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);
	}

	@Override
	public String getInventoryTitle() {
		return Lang.readMachinesText("CLAY_ROCKET_FUEL_GENERATOR");
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.REDSTONE_TORCH);
	}

	@Override
	public int getEnergyConsumption() {
		return 64;
	}

	@Override
	public int getSpeed() {
		return 1;
	}

	@Override
	public String getMachineIdentifier() {
		return "CLAY_ROCKET_FUEL_GENERATOR";
	}

	@Override
	public void registerDefaultRecipes() {
		this.registerRecipe(8, ClayTechMachineRecipes.MIXED_ROCKET_FUEL,
				new ItemStack[] { ClayTechItems.MIXED_ROCKET_FUEL });
	}

	@Override
	public int getCapacity() {
		return 256;
	}

}
