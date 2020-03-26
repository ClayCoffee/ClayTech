package club.claycoffee.ClayTech.api;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.World.Environment;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.utils.DataYML;
import club.claycoffee.ClayTech.utils.Utils;

/**
 * Planet. 星球.
 */
public class Planet {
	public String planetName;
	public ItemStack displayItem;
	public ChunkGenerator planetWorld;
	public Environment environment;
	public boolean habitable;
	public int gravity;
	public int distance;
	public int harmlevel;
	public boolean cold;
	public boolean spawnMob;
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

		if (Bukkit.getWorld(this.planetName) == null) {
			// Register
			WorldCreator newWorld = new WorldCreator(this.planetName);
			newWorld.environment(this.environment);
			long seed = new Random().nextLong();
			newWorld = newWorld.seed(seed);
			newWorld = newWorld.type(WorldType.NORMAL);
			newWorld = newWorld.generateStructures(false);
			newWorld = newWorld.generator(this.planetWorld);
			newWorld.createWorld();

			// 多世界注册
			if (Bukkit.getPluginManager().isPluginEnabled("Multiverse-Core")) {
				MVWorldManager wm = ((MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core"))
						.getMVWorldManager();
				wm.addWorld(trimWorldName(this.planetName), this.environment, null, null, null, null, true);
			}
		}
	}

	private String trimWorldName(String userInput) {
		return userInput.replaceAll("^[./\\\\]+", "");
	}
}
