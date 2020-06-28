package club.claycoffee.ClayTech;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import club.claycoffee.ClayTech.utils.DataYML;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Metrics;
import club.claycoffee.ClayTech.utils.PlanetUtils;
import club.claycoffee.ClayTech.utils.RocketUtils;
import club.claycoffee.ClayTech.utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import club.claycoffee.ClayTech.api.ClayTechManager;
import club.claycoffee.ClayTech.api.Planet;
import club.claycoffee.ClayTech.implementation.Planets.Earth;
import club.claycoffee.ClayTech.implementation.Planets.Mars;
import club.claycoffee.ClayTech.implementation.Planets.Moon;
import club.claycoffee.ClayTech.implementation.items.Armors;
import club.claycoffee.ClayTech.implementation.items.ClayFuelResource;
import club.claycoffee.ClayTech.implementation.items.Clay_basic;
import club.claycoffee.ClayTech.implementation.items.DrinkMakingStaff;
import club.claycoffee.ClayTech.implementation.items.Drinks;
import club.claycoffee.ClayTech.implementation.items.EffectItems;
import club.claycoffee.ClayTech.implementation.items.Elements;
import club.claycoffee.ClayTech.implementation.items.FoodMakingStaff;
import club.claycoffee.ClayTech.implementation.items.Foods;
import club.claycoffee.ClayTech.implementation.items.Golden_things;
import club.claycoffee.ClayTech.implementation.items.Ingots;
import club.claycoffee.ClayTech.implementation.items.MachineMakingBasic;
import club.claycoffee.ClayTech.implementation.items.PotionAffect_Weapons;
import club.claycoffee.ClayTech.implementation.items.Railways;
import club.claycoffee.ClayTech.implementation.items.RocketMakings;
import club.claycoffee.ClayTech.implementation.items.Rockets;
import club.claycoffee.ClayTech.implementation.items.Skulls;
import club.claycoffee.ClayTech.implementation.items.Tools;
import club.claycoffee.ClayTech.implementation.machines.CobbleStoneGenerator;
import club.claycoffee.ClayTech.implementation.machines.CraftingTable;
import club.claycoffee.ClayTech.implementation.machines.ElectricStoneCrusher;
import club.claycoffee.ClayTech.implementation.machines.ElectricWaterPump;
import club.claycoffee.ClayTech.implementation.machines.ElementExtracter;
import club.claycoffee.ClayTech.implementation.machines.ExperimentTableNormal;
import club.claycoffee.ClayTech.implementation.machines.FoodCauldron;
import club.claycoffee.ClayTech.implementation.machines.FoodChalkingMachine;
import club.claycoffee.ClayTech.implementation.machines.RocketAssemblingMachine;
import club.claycoffee.ClayTech.implementation.machines.RocketFuelGenerator;
import club.claycoffee.ClayTech.implementation.machines.RocketFuelInjector;
import club.claycoffee.ClayTech.implementation.machines.SpaceSuitOxygenInjector;
import club.claycoffee.ClayTech.implementation.resources.ClayFuel;
import club.claycoffee.ClayTech.listeners.*;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class ClayTech extends JavaPlugin implements SlimefunAddon {
	protected static ClayTech plugin;
	private static String locale;
	private static DataYML currentLangYML;
	private static FileConfiguration currentLang;
	private static DataYML planetYML;
	private static String highrailspeed;
	private static String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",")
			.split(",")[3];
	private static boolean compatible = true;
	private static List<Planet> planetList = new ArrayList<Planet>();
	private static String overworld = "";
	private static DataYML planetDataYML;
	private static ClayTechUpdater updater;
	private static boolean spacetravelneedperm;

	public static ClayTech getInstance() {
		return plugin;
	}

	public static String getLocale() {
		return locale;
	}

	public static DataYML getLangYML() {
		return currentLangYML;
	}

	public static boolean isSpaceTravelNeedPerm() {
		return spacetravelneedperm;
	}

	public static String getHighRailSpeed() {
		return highrailspeed;
	}

	public static ClayTechUpdater getUpdater() {
		return updater;
	}

	public static boolean getCompatible() {
		return compatible;
	}

	public static List<Planet> getPlanets() {
		return planetList;
	}

	public static String getOverworld() {
		return overworld;
	}

	public static DataYML getPlanetYML() {
		return planetYML;
	}

	public static DataYML getPlanetDataYML() {
		return planetDataYML;
	}

	@SuppressWarnings({ "unused", "static-access" })
	@Override
	public void onEnable() {
		plugin = this;
		// 当前研究ID: 9933
		this.saveDefaultConfig();
		FileConfiguration config = this.getConfig();
		locale = config.getString("Locale");
		if (locale == null)
			locale = "en-US";
		highrailspeed = config.getString("highrailspeed");
		if (highrailspeed == null)
			highrailspeed = "3";
		if (!Utils.ExitsInList(locale, Lang.LocaleList)) {
			Utils.info("§cLoading Error: Locale not found.Disableing plugin..");
			this.getServer().getPluginManager().disablePlugin(this);
		}
		// config
		try {
			FileConfiguration current = YamlConfiguration
					.loadConfiguration(new InputStreamReader(this.getResource("config.yml"), "UTF8"));
			List<String> currentconfig = new ArrayList<String>();
			for (String each : config.getKeys(false)) {
				currentconfig.add(each);
			}
			for (String each : current.getKeys(false)) {
				if (!Utils.ExitsInListL(each, currentconfig)) {
					try {
						if (current.isString(each)) {
							config.set(each, current.getString(each));
						}
						if (current.isInt(each)) {
							config.set(each, current.getInt(each));
						}
						if (current.isList(each)) {
							config.set(each, current.getList(each));
						}
						if (current.isBoolean(each)) {
							config.set(each, current.getBoolean(each));
						}

					} catch (Exception e) {
						Utils.info("§cThere is an error when reading the config file.Replacing the new config file..");
						config = current;
						this.saveConfig();
						this.reloadConfig();
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e2) {
			Utils.info("§cThere is an error when reading the config file.");
			e2.printStackTrace();
		}
		this.saveConfig();
		this.reloadConfig();
		overworld = config.getString("overworld");
		currentLangYML = new DataYML(locale + ".yml");
		currentLangYML.saveCDefaultConfig();
		currentLangYML.reloadCustomConfig();
		currentLang = currentLangYML.getCustomConfig();
		try {
			FileConfiguration current = YamlConfiguration
					.loadConfiguration(new InputStreamReader(this.getResource(locale + ".yml"), "UTF8"));
			for (String select : current.getKeys(false)) {
				if (Utils.sectionKeyToList(current.getConfigurationSection(select)).get(0).equalsIgnoreCase("null")
						|| current.getConfigurationSection(select).getKeys(false) == null) {
					currentLang.createPath(current.getConfigurationSection(select), select);
					currentLangYML.saveCustomConfig();
					continue;
				} else {
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
			Utils.info("§cThere is an error when reading the language file.Replacing the new language file..");
			e2.printStackTrace();
		}
		currentLangYML.saveCustomConfig();
		currentLangYML.reloadCustomConfig();
		switch (version) {
		case "v1_16_R1":
			break;
		case "v1_15_R1":
			break;
		case "v1_14_R1":
			break;
		case "v1_13_R2":
			break;
		case "v1_13_R1":
			break;
		default:
			compatible = false;
			break;
		}
		if (!compatible) {
			Utils.info(Lang.readGeneralText("Not_compatible"));
			this.getServer().getPluginManager().disablePlugin(this);
			return;
		}
		if (!SlimefunPlugin.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_15)) {
			Utils.info(Lang.readGeneralText("Before_115"));
		}
		Metrics mt = new Metrics(this, 6887);
		mt.addCustomChart(new Metrics.SimplePie("language", () -> languageCodeToLanguage(locale)));

		planetYML = new DataYML("planets.yml");
		planetYML.saveCDefaultConfig();
		planetYML.reloadCustomConfig();
		planetDataYML = new DataYML("planetsdata.yml");
		planetDataYML.saveCDefaultConfig();
		planetDataYML.reloadCustomConfig();
		Utils.info(Lang.readGeneralText("startTip"));
		Config cfg = new Config(this);
		Utils.info(Lang.readGeneralText("registeringItems"));
		try {
			registerSlimefun();
			registerPlanets();
			registerResources();
		} catch (Exception e) {
			Utils.info(Lang.readGeneralText("registeringError"));
			e.printStackTrace();
		}
		Bukkit.getPluginManager().registerEvents(new ItemInteractListener(), this);
		Bukkit.getPluginManager().registerEvents(new ItemUseListener(), this);
		Bukkit.getPluginManager().registerEvents(new FoodEatListener(), this);
		Bukkit.getPluginManager().registerEvents(new FoodDropListener(), this);
		Bukkit.getPluginManager().registerEvents(new WeaponListener(), this);
		Bukkit.getPluginManager().registerEvents(new RailwayListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlanetListener(), this);
		Bukkit.getPluginManager().registerEvents(new RocketLauncherListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlanetBaseListener(), this);

		this.getCommand("claytech").setExecutor(new ClayTechCommands());

		spacetravelneedperm = config.getBoolean("space-travel-need-perm");
//		Bukkit.getPluginManager().registerEvents(new Debug(), this);
		new BukkitRunnable() {

			@Override
			public void run() {
				// Updater
				updater = new ClayTechUpdater();
				if (!getConfig().getBoolean("disable-auto-updater")) {
					updater.tryUpdate();
					new BukkitRunnable() {

						@Override
						public void run() {
							updater.tryUpdate();
						}

					}.runTaskTimerAsynchronously(ClayTech.getInstance(), 72000, 72000);
				} else {
					Bukkit.getLogger().info(ChatColor.YELLOW + Lang.readGeneralText("Info_1"));
					Bukkit.getLogger().info(ChatColor.YELLOW + Lang.readGeneralText("Auto-updater_disabled"));
					Bukkit.getLogger().info(ChatColor.YELLOW + Lang.readGeneralText("Info_6"));
				}
				List<String> Authors = plugin.getDescription().getAuthors();
				Bukkit.getLogger().info(ChatColor.GREEN + Lang.readGeneralText("Info_1"));
				Bukkit.getLogger().info(ChatColor.GREEN + Lang.readGeneralText("Info_2").replaceAll("\\{version\\}",
						plugin.getDescription().getVersion()));
				Bukkit.getLogger().info(ChatColor.GREEN + Lang.readGeneralText("Info_3").replaceAll("\\{author\\}",
						Utils.ArrayToString(Authors.toArray(new String[Authors.size()]), ",", ".")));
				Bukkit.getLogger().info(ChatColor.GREEN + Lang.readGeneralText("Info_4"));
				Bukkit.getLogger().info(ChatColor.GREEN
						+ Lang.readGeneralText("Info_5").replaceAll("\\{issue_tracker\\}", plugin.getBugTrackerURL()));
				Bukkit.getLogger().info(ChatColor.GREEN + Lang.readGeneralText("Info_6"));
				for (Player player : Bukkit.getOnlinePlayers()) {
					Planet p = PlanetUtils.getPlanet(player.getWorld());
					if (p != null) {
						if (!p.getHabitable()) {
							World PreviousWorld = player.getWorld();
							new BukkitRunnable() {
								@SuppressWarnings("deprecation")
								@Override
								public void run() {
									if (!PreviousWorld.equals(player.getWorld()) || !player.isOnline()) {
										this.cancel();
										return;
									}
									if (!(ClayTechManager.isSpaceSuit(player.getInventory().getHelmet())
											&& ClayTechManager.isSpaceSuit(player.getInventory().getChestplate())
											&& ClayTechManager.isSpaceSuit(player.getInventory().getLeggings())
											&& ClayTechManager.isSpaceSuit(player.getInventory().getBoots()))) {
										// 扣血
										player.sendTitle(Lang.readGeneralText("SpaceSuitError"),
												Lang.readGeneralText("SpaceSuitError_Sub"));
										player.damage(5);

									} else {
										if (!(RocketUtils.getOxygen(player.getInventory().getHelmet()) > 0
												&& RocketUtils.getOxygen(player.getInventory().getChestplate()) > 0
												&& RocketUtils.getOxygen(player.getInventory().getLeggings()) > 0
												&& RocketUtils.getOxygen(player.getInventory().getBoots()) > 0)) {
											// 扣血
											player.sendTitle(Lang.readGeneralText("SpaceSuitError"),
													Lang.readGeneralText("SpaceSuitError_Sub"));
											player.damage(5);
										} else {
											int harmlevel = p.getHarmLevel();
											if (RocketUtils
													.getProtectLevel(player.getInventory().getHelmet()) < harmlevel
													|| RocketUtils.getProtectLevel(
															player.getInventory().getChestplate()) < harmlevel
													|| RocketUtils.getProtectLevel(
															player.getInventory().getLeggings()) < harmlevel
													|| RocketUtils.getProtectLevel(
															player.getInventory().getBoots()) < harmlevel) {
												// 扣血
												player.sendTitle(Lang.readGeneralText("SpaceSuitError"),
														Lang.readGeneralText("SpaceSuitError_Sub"));
												player.damage(5);
											}
										}
									}
								}

							}.runTaskTimer(ClayTech.getInstance(), 20, 20);
						}
					}
				}
			}

		}.runTaskAsynchronously(this);

	}

	@Override
	public void onDisable() {
	}

	private String languageCodeToLanguage(String code) {
		switch (code.toUpperCase()) {
		case "ZH-CN":
			return "Simplified Chinese";
		case "ZH-TW":
			return "Traditional Chinese";
		case "EN-US":
			return "English(US)";
		case "EN-UK":
			return "English(UK)";
		default:
			return code;
		}
	}

	private void registerSlimefun() {
		registerMachines();

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
		new EffectItems();
		new Ingots();
		new Tools();
		new ClayFuelResource();
		new RocketMakings();
		new Rockets();
	}

	public void registerMachines() {
		ItemStack[] ClayCrafingTable = { SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRO_MAGNET,
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY, new ItemStack(Material.CRAFTING_TABLE),
				SlimefunItems.BATTERY, ClayTechItems.MAGIC_CLAY, SlimefunItems.SMALL_CAPACITOR,
				ClayTechItems.MAGIC_CLAY };
		ItemStack[] ClayStoneCrusher = { SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRO_MAGNET,
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
				SlimefunItems.BATTERY, ClayTechItems.MAGIC_CLAY, SlimefunItems.SMALL_CAPACITOR,
				new ItemStack(Material.DISPENSER) };
		ItemStack[] ClayFoodCauldron = { SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRO_MAGNET,
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY, ClayTechItems.CLAY_CRAFTING_TABLE,
				SlimefunItems.BATTERY, ClayTechItems.MAGIC_CLAY, SlimefunItems.MEDIUM_CAPACITOR,
				ClayTechItems.MAGIC_CLAY };
		ItemStack[] ClayChalkingMachine = { SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRO_MAGNET,
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY, ClayTechItems.CLAY_CRAFTING_TABLE,
				SlimefunItems.BATTERY, ClayTechItems.CLAY_STICK, SlimefunItems.MEDIUM_CAPACITOR,
				ClayTechItems.MAGIC_CLAY };
		ItemStack[] ClayElementExtracter = { ClayTechItems.BLISTERING_CORE, ClayTechItems.BLISTERING_CORE,
				ClayTechItems.BLISTERING_CORE, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.PROGRAMMABLE_ANDROID,
				SlimefunItems.WITHER_PROOF_OBSIDIAN };
		ItemStack[] ClayExperimentTableBasic = { ClayTechItems.CLAY_ALLOY_INGOT, SlimefunItems.ELECTRIC_MOTOR,
				ClayTechItems.CLAY_ALLOY_INGOT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, ClayTechItems.CLAY_FOOD_CAULDRON,
				ClayTechItems.BLISTERING_CORE, ClayTechItems.CLAY_ALLOY_INGOT, ClayTechItems.ELEMENT_UNIT,
				ClayTechItems.CLAY_ALLOY_INGOT };
		ItemStack[] ClayRocketAssemblingMachine = { SlimefunItems.ELECTRIC_MOTOR, ClayTechItems.BLISTERING_CORE,
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.WITHER_PROOF_OBSIDIAN,
				SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.MEDIUM_CAPACITOR,
				SlimefunItems.PROGRAMMABLE_ANDROID, SlimefunItems.MEDIUM_CAPACITOR };
		ItemStack[] ClayRocketFuelGenerator = { SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR,
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.WITHER_PROOF_OBSIDIAN,
				SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.SMALL_CAPACITOR, ClayTechItems.CLAY_FUSION_INGOT,
				SlimefunItems.SMALL_CAPACITOR };
		ItemStack[] ClayRocketFuelInjector = { SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR,
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.WITHER_PROOF_OBSIDIAN,
				SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.SMALL_CAPACITOR, ClayTechItems.BLISTERING_CORE,
				SlimefunItems.SMALL_CAPACITOR };
		ItemStack[] ClaySpaceSuitOxygenInjector = { SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR,
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
				ClayTechItems.CLAY_ROCKET_FUEL_INJECTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
				SlimefunItems.SMALL_CAPACITOR, ClayTechItems.OXYGEN_TANK, SlimefunItems.SMALL_CAPACITOR };
		ItemStack[] ClayCobbleStoneGenerator = { SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.WATER_BUCKET),
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
				SlimefunItems.PROGRAMMABLE_ANDROID_MINER, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
				SlimefunItems.SMALL_CAPACITOR, new ItemStack(Material.LAVA_BUCKET), SlimefunItems.SMALL_CAPACITOR };
		ItemStack[] ClayElectricWaterPump = { SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.DISPENSER),
				SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
				SlimefunItems.PROGRAMMABLE_ANDROID_MINER, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
				SlimefunItems.SMALL_CAPACITOR, new ItemStack(Material.DISPENSER), SlimefunItems.SMALL_CAPACITOR };

		// 机器
		SlimefunItemStack craftingtable = new SlimefunItemStack("CLAY_CRAFTING_TABLE",
				ClayTechItems.CLAY_CRAFTING_TABLE);
		SlimefunItemStack foodcauldron = new SlimefunItemStack("CLAY_FOOD_CAULDRON", ClayTechItems.CLAY_FOOD_CAULDRON);
		SlimefunItemStack chalkingmachine = new SlimefunItemStack("CLAY_FOOD_CHALKING_MACHINE",
				ClayTechItems.CLAY_FOOD_CHALKING_MACHINE);
		SlimefunItemStack elementextracter = new SlimefunItemStack("CLAY_ELEMENT_EXTRACTER",
				ClayTechItems.CLAY_ELEMENT_EXTRACTER);
		SlimefunItemStack electricstonecrusher = new SlimefunItemStack("CLAY_ELECTRIC_STONE_CRUSHER",
				ClayTechItems.CLAY_ELECTRIC_STONE_CRUSHER);
		SlimefunItemStack experimenttablebasic = new SlimefunItemStack("CLAY_EXPERIMENT_TABLE_BASIC",
				ClayTechItems.CLAY_EXPERIMENT_TABLE_NORMAL);
		SlimefunItemStack rocketassemblingmachine = new SlimefunItemStack("CLAY_ROCKET_ASSEMBLING_MACHINE",
				ClayTechItems.CLAY_ROCKET_ASSEMBLING_MACHINE);
		SlimefunItemStack rocketfuelgenerator = new SlimefunItemStack("CLAY_ROCKET_FUEL_GENERATOR",
				ClayTechItems.CLAY_ROCKET_FUEL_GENERATOR);
		SlimefunItemStack rocketfuelinjector = new SlimefunItemStack("CLAY_ROCKET_FUEL_INJECTOR",
				ClayTechItems.CLAY_ROCKET_FUEL_INJECTOR);
		SlimefunItemStack spacesuitoxygeninjector = new SlimefunItemStack("CLAY_SPACESUIT_OXYGEN_INJECTOR",
				ClayTechItems.CLAY_SPACESUIT_OXYGEN_INJECTOR);
		SlimefunItemStack cobblestonegenerator = new SlimefunItemStack("CLAY_COBBLESTONE_GENERATOR",
				ClayTechItems.CLAY_COBBLESTONE_GENERATOR);
		SlimefunItemStack electricwaterpump = new SlimefunItemStack("CLAY_ELECTRIC_WATER_PUMP",
				ClayTechItems.CLAY_ELECTRIC_WATER_PUMP);

		new CraftingTable(ClayTechItems.C_MACHINES, craftingtable, "CLAY_CRAFTING_TABLE",
				RecipeType.ENHANCED_CRAFTING_TABLE, ClayCrafingTable).register(this);
		new ElectricStoneCrusher(ClayTechItems.C_MACHINES, electricstonecrusher, "CLAY_ELECTRIC_STONE_CRUSHER",
				RecipeType.ENHANCED_CRAFTING_TABLE, ClayStoneCrusher).register(this);
		new FoodCauldron(ClayTechItems.C_MACHINES, foodcauldron, "CLAY_FOOD_CAULDRON",
				RecipeType.ENHANCED_CRAFTING_TABLE, ClayFoodCauldron).register(this);
		new FoodChalkingMachine(ClayTechItems.C_MACHINES, chalkingmachine, "CLAY_FOOD_CHALKING_MACHINE",
				RecipeType.ENHANCED_CRAFTING_TABLE, ClayChalkingMachine).register(this);
		new ElementExtracter(ClayTechItems.C_MACHINES, elementextracter, "CLAY_ELEMENT_EXTRACTER",
				RecipeType.ENHANCED_CRAFTING_TABLE, ClayElementExtracter).register(this);
		new ExperimentTableNormal(ClayTechItems.C_MACHINES, experimenttablebasic, "CLAY_EXPERIMENT_TABLE_BASIC",
				RecipeType.ENHANCED_CRAFTING_TABLE, ClayExperimentTableBasic).register(this);
		new RocketAssemblingMachine(ClayTechItems.C_MACHINES, rocketassemblingmachine, "CLAY_ROCKET_ASSEMBLING_MACHINE",
				RecipeType.ENHANCED_CRAFTING_TABLE, ClayRocketAssemblingMachine).register(this);
		new RocketFuelGenerator(ClayTechItems.C_MACHINES, rocketfuelgenerator, "CLAY_ROCKET_FUEL_GENERATOR",
				RecipeType.ENHANCED_CRAFTING_TABLE, ClayRocketFuelGenerator).register(this);
		new RocketFuelInjector(ClayTechItems.C_MACHINES, rocketfuelinjector, "CLAY_ROCKET_FUEL_INJECTOR",
				RecipeType.ENHANCED_CRAFTING_TABLE, ClayRocketFuelInjector).register(this);
		new SpaceSuitOxygenInjector(ClayTechItems.C_MACHINES, spacesuitoxygeninjector, "CLAY_SPACESUIT_OXYGEN_INJECTOR",
				RecipeType.ENHANCED_CRAFTING_TABLE, ClaySpaceSuitOxygenInjector).register(this);
		new CobbleStoneGenerator(ClayTechItems.C_MACHINES, cobblestonegenerator, "CLAY_COBBLESTONE_GENERATOR",
				RecipeType.ENHANCED_CRAFTING_TABLE, ClayCobbleStoneGenerator).register(this);
		new ElectricWaterPump(ClayTechItems.C_MACHINES, electricwaterpump, "CLAY_ELECTRIC_WATER_PUMP",
				RecipeType.ENHANCED_CRAFTING_TABLE, ClayElectricWaterPump).register(this);
	}

	@Override
	public JavaPlugin getJavaPlugin() {
		return this;
	}

	@Override
	public File getFile() {
		return super.getFile();
	}

	@Override
	public String getBugTrackerURL() {
		return "https://github.com/ClayCoffee/ClayTech/issues";
	}

	private void registerPlanets() {
		// Earth(Overworld) 地球(主世界)
		new Earth();
		// Moon 月球
		new Moon();
		// Mars 火星
		new Mars();
	}

	private void registerResources() {
		new ClayFuel();
	}

	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		List<String> PlanetNameList = new ArrayList<String>();
		List<Planet> PlanetList = getPlanets();
		for (Planet p : PlanetList) {
			PlanetNameList.add(p.getPlanetWorldName());
		}
		if (Utils.ExitsInList(id, PlanetNameList.toArray(new String[PlanetNameList.size()]))) {
			return PlanetList.get(PlanetNameList.indexOf(id)).getPlanetGenerator();
		}
		return Bukkit.getWorld(getOverworld()).getGenerator();
	}

}
