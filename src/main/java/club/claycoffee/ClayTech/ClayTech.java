package club.claycoffee.ClayTech;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import club.claycoffee.ClayTech.listener.*;
import club.claycoffee.ClayTech.utils.Utils;
import club.claycoffee.ClayTech.items.*;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

@SuppressWarnings("deprecation")
public class ClayTech extends JavaPlugin {
	public static ClayTech plugin;

	@SuppressWarnings("unused")
	@Override
	public void onEnable() {
		// 当前研究ID: 9916
		
		Utils.info("粘土科技 By ClayCoffee，请不要未经我的授权使用本插件在你的服务器上(如果使用在基友服,且人数少于4人,默认授权。而且你也不能未经允许分发编译后的插件.谢谢合作!");
		plugin = this;
		Config cfg = new Config(this);
		Utils.info("注册粘液科技物品中...");
		try {
			registerSlimefun();
		}
		catch(Exception e) {Utils.info("在注册物品时发生错误:");e.printStackTrace();}
		Bukkit.getPluginManager().registerEvents(new ClayTechListener(), this);
	}

	@Override
	public void onDisable() {
	}

	public static void registerSlimefun() {
		ItemStack[] ClayCrafingTable = {SlimefunItems.ELECTRIC_MOTOR,SlimefunItems.ELECTRO_MAGNET,SlimefunItems.ELECTRIC_MOTOR,SlimefunItems.BATTERY,new ItemStack(Material.CRAFTING_TABLE),SlimefunItems.BATTERY,Defines.MAGIC_CLAY,SlimefunItems.SMALL_CAPACITOR,Defines.MAGIC_CLAY};
		ItemStack[] ClayFoodCauldron = {SlimefunItems.ELECTRIC_MOTOR,SlimefunItems.ELECTRO_MAGNET,SlimefunItems.ELECTRIC_MOTOR,SlimefunItems.BATTERY,Defines.CLAY_CRAFTING_TABLE,SlimefunItems.BATTERY,Defines.MAGIC_CLAY,SlimefunItems.MEDIUM_CAPACITOR,Defines.MAGIC_CLAY};
		ItemStack[] ClayChalkingMachine = {SlimefunItems.ELECTRIC_MOTOR,SlimefunItems.ELECTRO_MAGNET,SlimefunItems.ELECTRIC_MOTOR,SlimefunItems.BATTERY,Defines.CLAY_CRAFTING_TABLE,SlimefunItems.BATTERY,Defines.CLAY_STICK,SlimefunItems.MEDIUM_CAPACITOR,Defines.MAGIC_CLAY};
		
		// 机器
		SlimefunItemStack craftingtable = new SlimefunItemStack("CLAY_CRAFTING_TABLE",Defines.CLAY_CRAFTING_TABLE);
		SlimefunItemStack foodcauldron = new SlimefunItemStack("CLAY_FOOD_CAULDRON",Defines.CLAY_FOOD_CAULDRON);
		SlimefunItemStack chalkingmachine = new SlimefunItemStack("CLAY_FOOD_CHALKING_MACHINE",Defines.CLAY_FOOD_CHALKING_MACHINE);
		new CraftingTable(Defines.C_BASICS,craftingtable,"CLAY_CRAFTING_TABLE",RecipeType.ENHANCED_CRAFTING_TABLE,ClayCrafingTable).registerChargeableBlock(128);
		new FoodCauldron(Defines.C_BASICS,foodcauldron,"CLAY_FOOD_CAULDRON",RecipeType.ENHANCED_CRAFTING_TABLE,ClayFoodCauldron).registerChargeableBlock(512);
		new FoodChalkingMachine(Defines.C_BASICS,chalkingmachine,"CLAY_FOOD_CHALKING_MACHINE",RecipeType.ENHANCED_CRAFTING_TABLE,ClayChalkingMachine).registerChargeableBlock(512);
		
		// 物品
		new Clay_basic();
		new PotionAffect_Weapons();
		new Golden_things();
		new Skulls();
		new Armors();
		new DrinkMakingStaff();
		new Drinks();
		new FoodMakingStaff();
		new Foods();
		
		
		
	}
}
