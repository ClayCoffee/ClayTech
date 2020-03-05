package club.claycoffee.ClayTech.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.Defines;
import club.claycoffee.ClayTech.Recipes;
import club.claycoffee.ClayTech.Machines.ACraftingTable;
import club.claycoffee.ClayTech.utils.Lang;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.LockedCategory;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class FoodCauldron extends ACraftingTable{

	public FoodCauldron(LockedCategory category, SlimefunItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item,id, recipeType, recipe);
	}

	@Override
	public String getInventoryTitle() {
		return Lang.readMachinesText("CLAY_FOOD_CAULDRON");
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.CAMPFIRE);
	}

	@Override
	public int getEnergyConsumption() {
		return 32;
	}

	@Override
	public int getSpeed() {
		return 1;
	}

	@Override
	public String getMachineIdentifier() {
		return "CLAY_FOOD_CAULDRON";
	}

	@Override
	public void registerDefaultRecipes() {
		
		this.registerRecipe(30,Recipes.CHICKEN_FOOT,new ItemStack[]{Defines.CHICKEN_FOOT});
		this.registerRecipe(30,Recipes.SPICY_CHICKEN_BURGER,new ItemStack[]{Defines.SPICY_CHICKEN_BURGER});
		this.registerRecipe(30,Recipes.BABA_BURGER,new ItemStack[]{Defines.BABA_BURGER});
		this.registerRecipe(30,Recipes.CHOCOLATE,new ItemStack[]{Defines.CHOCOLATE});
		this.registerRecipe(30,Recipes.SNAIL_FOOD,new ItemStack[]{Defines.SNAIL_FOOD});
		this.registerRecipe(10,Recipes.HONEY_SWEET,new ItemStack[]{Defines.HONEY_SWEET});
		
		this.registerRecipe(30,Recipes.CLAY_COFFEE,new ItemStack[]{Defines.CLAY_COFFEE});
		this.registerRecipe(30,Recipes.LEMON_POWDER_DRINK,new ItemStack[]{Defines.LEMON_POWDER_DRINK});
		this.registerRecipe(30,Recipes.TEA_DRINK,new ItemStack[]{Defines.TEA_DRINK});
		this.registerRecipe(30,Recipes.LEMON_TEA_DRINK,new ItemStack[]{Defines.LEMON_TEA_DRINK});
	}

	@Override
	public int getCapacity() {
		return 512;
	}
}