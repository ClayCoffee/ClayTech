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

public class CraftingTable extends ACraftingTable{

	public CraftingTable(LockedCategory category, SlimefunItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item,id, recipeType, recipe);
	}

	@Override
	public String getInventoryTitle() {
		return Lang.readMachinesText("CLAY_FUSION_MACHINE");
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.CRAFTING_TABLE);
	}

	@Override
	public int getEnergyConsumption() {
		return 16;
	}

	@Override
	public int getSpeed() {
		return 1;
	}

	@Override
	public String getMachineIdentifier() {
		return "CLAY_CRAFTING_TABLE";
	}
	@Override
	public int getCapacity() {
		return 128;
	}
	@Override
	public void registerDefaultRecipes() {
		this.registerRecipe(20,Recipes.BLIND_CORE,new ItemStack[]{Defines.BLIND_CORE});
		this.registerRecipe(100,Recipes.BLIND_SWORD,new ItemStack[]{Defines.BLIND_SWORD});
		this.registerRecipe(20,Recipes.POISON_EYE,new ItemStack[]{Defines.POISON_EYE});
		this.registerRecipe(20,Recipes.POISON_CORE,new ItemStack[]{Defines.POISON_CORE});
		this.registerRecipe(40,Recipes.ADVANCED_CONFUSION_CORE,new ItemStack[]{Defines.ADVANCED_POISON_CORE});
		this.registerRecipe(20,Recipes.CONFUSION_CORE,new ItemStack[]{Defines.CONFUSION_CORE});
		this.registerRecipe(40,Recipes.ADVANCED_CONFUSION_CORE,new ItemStack[]{Defines.ADVANCED_CONFUSION_CORE});
		this.registerRecipe(20,Recipes.BLACK_ROCK_BLOCK,new ItemStack[]{Defines.BLACK_ROCK_BLOCK});
		this.registerRecipe(20,Recipes.SLOWNESS_CORE,new ItemStack[]{Defines.SLOWNESS_CORE});
		this.registerRecipe(40,Recipes.ADVANCED_SLOWNESS_CORE,new ItemStack[]{Defines.ADVANCED_SLOWNESS_CORE});
		this.registerRecipe(40,Recipes.ADVANCED_BLIND_CORE,new ItemStack[]{Defines.ADVANCED_BLIND_CORE});
		this.registerRecipe(400,Recipes.FOUR_BOW,new ItemStack[]{Defines.FOUR_BOW});
		this.registerRecipe(100,Recipes.POISON_SWORD,new ItemStack[]{Defines.POISON_SWORD});
		this.registerRecipe(100,Recipes.ANTI_SLOWNESS_BOOTS,new ItemStack[]{Defines.ANTI_SLOWNESS_BOOTS});
		this.registerRecipe(80,Recipes.BLISTERING_CORE,new ItemStack[]{Defines.BLISTERING_CORE});
		this.registerRecipe(30,Recipes.ELEMENT_UNIT,new ItemStack[]{Defines.ELEMENT_UNIT});
	}

}
