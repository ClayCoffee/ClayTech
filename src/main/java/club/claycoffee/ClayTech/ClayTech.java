package club.claycoffee.ClayTech;

import java.io.InputStreamReader;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import club.claycoffee.ClayTech.listener.*;
import club.claycoffee.ClayTech.utils.DataYML;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import club.claycoffee.ClayTech.items.*;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

@SuppressWarnings("deprecation")
public class ClayTech extends JavaPlugin implements SlimefunAddon{
	public static ClayTech plugin;
	public static String locale;
	public static DataYML currentLangYML;
	public static FileConfiguration currentLang;

	@SuppressWarnings({ "unused", "static-access" })
	@Override
	public void onEnable() {
		plugin = this;
		// 当前研究ID: 9920
		this.saveDefaultConfig();
		FileConfiguration config = this.getConfig();
		locale = config.getString("Locale");
		if (locale == null)
			locale = "en-US";
		if (!Utils.ExitsInList(locale, Lang.LocaleList)) {
			Utils.info("§cLoading Error: Locale not found.Disableing plugin..");
			this.getServer().getPluginManager().disablePlugin(this);
		}
		currentLangYML = new DataYML(locale + ".yml");
		currentLangYML.saveCDefaultConfig();
		currentLangYML.reloadCustomConfig();
		currentLang = currentLangYML.getCustomConfig();
		try {
			FileConfiguration current = YamlConfiguration
					.loadConfiguration(new InputStreamReader(this.getResource(locale + ".yml"),"UTF8"));
			for (String select : current.getKeys(false)) {
				if(Utils.sectionKeyToList(current.getConfigurationSection(select)).get(0).equalsIgnoreCase("null") || current.getConfigurationSection(select).getKeys(false) == null) {
					currentLang.createPath(current.getConfigurationSection(select), select);
					currentLangYML.saveCustomConfig();
					continue;
				}
				else {
					List<String> found = Utils.sectionKeyToList(current.getConfigurationSection(select));
					List<String> found2 = Utils.sectionKeyToList(currentLang.getConfigurationSection(select));
					for (String each : found) {
						if (!Utils.ExitsInListL(each, found2)) {
							try {
								if (current.isString(select + "." + each)) {
									currentLang.set(select + "." + each, current.getString(select + "." + each));
								}
								if (current.isInt(select + "." + each)) {
									currentLang.set(select + "." + each, current.getInt(select + "." + each));
								}
								if (current.isList(select + "." + each)) {
									currentLang.set(select + "." + each, current.getList(select + "." + each));
								}
							} catch (Exception e) {
								Utils.info(
										"§cThere is an error when reading the language file.Replacing the new language file..");
								currentLang = current;
								currentLangYML.saveCustomConfig();
								currentLangYML.reloadCustomConfig();
								e.printStackTrace();
							}
						}
					}
				}
			}
		} catch (Exception e2) {
			Utils.info(
					"§cThere is an error when reading the language file.Replacing the new language file..");
			e2.printStackTrace();
		}
		currentLangYML.saveCustomConfig();
		currentLangYML.reloadCustomConfig();
		Utils.info(Lang.readGeneralText("startTip"));
		Config cfg = new Config(this);
		if(this.getServer().getVersion().equals("1.15")) {
			
		}
		else {
			
		}
		Utils.info(Lang.readGeneralText("registeringItems"));
		try {
			registerSlimefun();
		} catch (Exception e) {
			Utils.info(Lang.readGeneralText("registeringError"));
			e.printStackTrace();
		}
		Bukkit.getPluginManager().registerEvents(new ClayTechListener(), this);
	}

	@Override
	public void onDisable() {
	}

	public void registerSlimefun() {
		ItemStack[] ClayCrafingTable = { SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRO_MAGNET,
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY, new ItemStack(Material.CRAFTING_TABLE),
				SlimefunItems.BATTERY, Defines.MAGIC_CLAY, SlimefunItems.SMALL_CAPACITOR, Defines.MAGIC_CLAY };
		ItemStack[] ClayStoneCrusher = { SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRO_MAGNET,
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
				SlimefunItems.BATTERY, Defines.MAGIC_CLAY, SlimefunItems.SMALL_CAPACITOR, new ItemStack(Material.DISPENSER) };
		ItemStack[] ClayFoodCauldron = { SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRO_MAGNET,
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY, Defines.CLAY_CRAFTING_TABLE, SlimefunItems.BATTERY,
				Defines.MAGIC_CLAY, SlimefunItems.MEDIUM_CAPACITOR, Defines.MAGIC_CLAY };
		ItemStack[] ClayChalkingMachine = { SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRO_MAGNET,
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY, Defines.CLAY_CRAFTING_TABLE, SlimefunItems.BATTERY,
				Defines.CLAY_STICK, SlimefunItems.MEDIUM_CAPACITOR, Defines.MAGIC_CLAY };
		ItemStack[] ClayElementExtracter = { Defines.BLISTERING_CORE, Defines.BLISTERING_CORE, Defines.BLISTERING_CORE,
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ELECTRIC_MOTOR,
				SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.PROGRAMMABLE_ANDROID_3,
				SlimefunItems.WITHER_PROOF_OBSIDIAN };

		// 机器
		SlimefunItemStack craftingtable = new SlimefunItemStack("CLAY_CRAFTING_TABLE", Defines.CLAY_CRAFTING_TABLE);
		SlimefunItemStack foodcauldron = new SlimefunItemStack("CLAY_FOOD_CAULDRON", Defines.CLAY_FOOD_CAULDRON);
		SlimefunItemStack chalkingmachine = new SlimefunItemStack("CLAY_FOOD_CHALKING_MACHINE",
				Defines.CLAY_FOOD_CHALKING_MACHINE);
		SlimefunItemStack elementextracter = new SlimefunItemStack("CLAY_ELEMENT_EXTRACTER",
				Defines.CLAY_ELEMENT_EXTRACTER);
		SlimefunItemStack electricstonecrusher = new SlimefunItemStack("CLAY_ELECTRIC_STONE_CRUSHER",
				Defines.CLAY_ELECTRIC_STONE_CRUSHER);

		new CraftingTable(Defines.C_MACHINES, craftingtable, "CLAY_CRAFTING_TABLE", RecipeType.ENHANCED_CRAFTING_TABLE,
				ClayCrafingTable).register(this);
		new ElectricStoneCrusher(Defines.C_MACHINES, electricstonecrusher, "CLAY_ELECTRIC_STONE_CRUSHER", RecipeType.ENHANCED_CRAFTING_TABLE,
				ClayStoneCrusher).register(this);
		new FoodCauldron(Defines.C_MACHINES, foodcauldron, "CLAY_FOOD_CAULDRON", RecipeType.ENHANCED_CRAFTING_TABLE,
				ClayFoodCauldron).register(this);
		new FoodChalkingMachine(Defines.C_MACHINES, chalkingmachine, "CLAY_FOOD_CHALKING_MACHINE",
				RecipeType.ENHANCED_CRAFTING_TABLE, ClayChalkingMachine).register(this);
		new ElementExtracter(Defines.C_MACHINES, elementextracter, "CLAY_ELEMENT_EXTRACTER",
				RecipeType.ENHANCED_CRAFTING_TABLE, ClayElementExtracter).register(this);

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
		new MachineMakingBasic();
		new Elements();
		new Railways();

	}

	@Override
	public JavaPlugin getJavaPlugin() {
		return this;
	}

	@Override
	public String getBugTrackerURL() {
		// TODO 自动生成的方法存根
		return null;
	}
}
