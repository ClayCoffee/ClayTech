package club.claycoffee.ClayTech.api;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.World.Environment;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.utils.DataYML;
import club.claycoffee.ClayTech.utils.Utils;

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
		newWorld.createWorld();
	}

	public void setDistance(int newDistance) {
		this.distance = newDistance;
	}

	public void setHarmLevel(int newLevel) {
		this.harmlevel = newLevel;
	}

	public void setHabitable(boolean habitable) {
		this.habitable = habitable;
	}

	public void setCold(boolean cold) {
		this.cold = cold;
	}

	public void setGravity(int newGravity) {
		this.gravity = newGravity;
	}

	public int getDistance() {
		return this.distance;
	}

	public int getHarmLevel() {
		return this.harmlevel;
	}

	public boolean getHabitable() {
		return this.habitable;
	}

	public boolean getCold() {
		return this.cold;
	}

	public boolean getMobSpawnable() {
		return this.spawnMob;
	}

	public int getGravity() {
		return this.gravity;
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
