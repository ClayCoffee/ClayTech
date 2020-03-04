package club.claycoffee.ClayTech.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.Machines.ANewContainer;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.LockedCategory;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class FoodChalkingMachine extends ANewContainer{

	public FoodChalkingMachine(LockedCategory category, SlimefunItemStack item, String id, RecipeType recipeType,
			ItemStack[] recipe) {
		super(category, item,id, recipeType, recipe);
	}

	@Override
	public String getInventoryTitle() {
		return "§b食物打粉机";
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
		this.registerRecipe(8, new ItemStack[] {Defines.RAW_TEA}, new ItemStack[] {Defines.TEA_POWDER});
		this.registerRecipe(8, new ItemStack[] {Defines.LEMON}, new ItemStack[] {Defines.LEMON_POWDER});
	}

}
