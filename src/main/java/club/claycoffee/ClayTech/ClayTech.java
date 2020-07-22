package club.claycoffee.ClayTech;

import club.claycoffee.ClayTech.api.ClayTechManager;
import club.claycoffee.ClayTech.api.Planet;
import club.claycoffee.ClayTech.implementation.Planets.Earth;
import club.claycoffee.ClayTech.implementation.Planets.Mars;
import club.claycoffee.ClayTech.implementation.Planets.Moon;
import club.claycoffee.ClayTech.implementation.items.*;
import club.claycoffee.ClayTech.implementation.resources.ClayFuel;
import club.claycoffee.ClayTech.listeners.*;
import club.claycoffee.ClayTech.utils.*;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
    private static String updateBranch;
    private static FileConfiguration config;

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

    public static String getUpdateBranch() {
        return updateBranch;
    }

    @SuppressWarnings({"unused", "static-access"})
    @Override
    public void onEnable() {
        plugin = this;
        // 当前研究ID: 9934
        this.saveDefaultConfig();
        config = this.getConfig();
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

        ClayTechData.currentVersion = this.getDescription().getVersion();
//		Bukkit.getPluginManager().registerEvents(new Debug(), this);
        new BukkitRunnable() {

            @Override
            public void run() {
                // Updater
                updateBranch = config.getString("update-branch");
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
        new Machines();

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
