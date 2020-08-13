package cn.claycoffee.ClayTech.api;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.utils.DataYML;
import cn.claycoffee.ClayTech.utils.Utils;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import org.bukkit.*;
import org.bukkit.World.Environment;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * Planet. 星球.
 */
public class Planet {
    private String planetName;
    private ItemStack displayItem;
    private ChunkGenerator planetWorld;
    private Environment environment;
    private boolean habitable;
    private int gravity;
    private int distance;
    private int harmlevel;
    private boolean cold;
    private boolean spawnMob;
    private DataYML planets = ClayTech.getPlanetYML();
    private FileConfiguration f = planets.getCustomConfig();

    public Planet(String planetName, ItemStack displayItem, ChunkGenerator planetWorld, Environment environment,
                  boolean habitable, int gravity, int distance, int harmlevel, boolean cold) {
        this.planetName = planetName;
        this.displayItem = displayItem;
        this.planetWorld = planetWorld;
        this.environment = environment;
        this.habitable = habitable;
        this.gravity = gravity;
        this.distance = distance;
        this.harmlevel = harmlevel;
        this.cold = cold;
        if (!f.contains(this.planetName)) {
            if (this.planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
                f.set(this.planetName, true);
            } else {
                f.set(this.planetName, false);
            }
            planets.saveCustomConfig();
            planets.reloadCustomConfig();
        }
        if (!f.contains(this.planetName + "-spawnMobs")) {
            if (this.planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
                f.set(this.planetName + "-spawnMobs", true);
                this.spawnMob = true;
            } else {
                f.set(this.planetName + "-spawnMobs", false);
                this.spawnMob = false;
            }
            planets.saveCustomConfig();
            planets.reloadCustomConfig();
        } else {
            this.spawnMob = f.getBoolean(this.planetName + "-spawnMobs");
        }

    }

    public Planet(String planetName, ItemStack displayItem, World planetWorld, Environment environment,
                  boolean habitable, int gravity, int distance, int harmlevel, boolean cold) {
        this.planetName = planetName;
        this.displayItem = displayItem;
        this.planetWorld = planetWorld.getGenerator();
        this.environment = environment;
        this.habitable = habitable;
        this.gravity = gravity;
        this.harmlevel = harmlevel;
        this.cold = cold;
        if (!f.contains(this.planetName)) {
            if (this.planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
                f.set(this.planetName, true);
            } else {
                f.set(this.planetName, false);
            }
            planets.saveCustomConfig();
            planets.reloadCustomConfig();
        }
        if (!f.contains(this.planetName + "-spawnMobs")) {
            if (this.planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
                f.set(this.planetName + "-spawnMobs", true);
                this.spawnMob = true;
            } else {
                f.set(this.planetName + "-spawnMobs", false);
                this.spawnMob = false;
            }
            planets.saveCustomConfig();
            planets.reloadCustomConfig();
        } else {
            this.spawnMob = f.getBoolean(this.planetName + "-spawnMobs");
        }
    }

    public void register() {
        if (planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
            if (!f.getBoolean(this.planetName) && !this.planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
                if (Bukkit.getWorld(this.planetName) != null) {
                    Bukkit.unloadWorld(this.planetName, true);
                }
                return;
            }
            for (Planet each : ClayTech.getPlanets()) {
                if (each.planetName.equalsIgnoreCase(this.planetName)) {
                    Utils.info("Registering Error: Planet" + this.planetName + "already exists!");
                    return;
                }
            }
            ClayTech.getPlanets().add(this);
            return;
        }

        if (!f.getBoolean(this.planetName) && !this.planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
            if (Bukkit.getWorld(this.planetName) != null) {
                Bukkit.unloadWorld(this.planetName, true);
            }
            return;
        }
        for (Planet each : ClayTech.getPlanets()) {
            if (each.planetName.equalsIgnoreCase(this.planetName)) {
                Utils.info("Registering Error: Planet" + this.planetName + "already exists!");
                return;
            }
        }
        ClayTech.getPlanets().add(this);

        WorldCreator newWorld = new WorldCreator(this.planetName);
        if (Bukkit.getWorld(this.planetName) == null) {
            newWorld.environment(this.environment);
            long seed = new Random().nextLong();
            newWorld = newWorld.seed(seed);
            newWorld = newWorld.type(WorldType.NORMAL);
            newWorld = newWorld.generateStructures(false);
            newWorld = newWorld.generator("ClayTech:" + this.planetName);
            World w = newWorld.createWorld();
            if (this.cold) {
                // 如果冷就不会下雨
                w.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            }

            return;
        }
        try {
            newWorld.createWorld();
        } catch (Exception e) {
        }


        new BukkitRunnable() {

            @Override
            public void run() {
                if (Bukkit.getPluginManager().isPluginEnabled("Multiverse-Core")) {
                    MVWorldManager wm = ((MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core"))
                            .getMVWorldManager();
                    wm.addWorld(trimWorldName(planetName), environment, null, WorldType.NORMAL, null,
                            "ClayTech:" + planetName, true);
                }

            }

        }.runTaskAsynchronously(ClayTech.getInstance());
    }

    private String trimWorldName(String userInput) {
        return userInput.replaceAll("^[./\\\\]+", "");
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int newDistance) {
        this.distance = newDistance;
    }

    public int getHarmLevel() {
        return this.harmlevel;
    }

    public void setHarmLevel(int newLevel) {
        this.harmlevel = newLevel;
    }

    public boolean getHabitable() {
        return this.habitable;
    }

    public void setHabitable(boolean habitable) {
        this.habitable = habitable;
    }

    public boolean getCold() {
        return this.cold;
    }

    public void setCold(boolean cold) {
        this.cold = cold;
    }

    public boolean getMobSpawnable() {
        return this.spawnMob;
    }

    public int getGravity() {
        return this.gravity;
    }

    public void setGravity(int newGravity) {
        this.gravity = newGravity;
    }

    public String getPlanetWorldName() {
        return this.planetName;
    }

    public ItemStack getDisplayStack() {
        return this.displayItem.clone();
    }

    public ChunkGenerator getPlanetGenerator() {
        return this.planetWorld;
    }
}
