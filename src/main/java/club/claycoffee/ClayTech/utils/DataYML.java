package club.claycoffee.ClayTech.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class DataYML {
	private FileConfiguration config = null;
	private File configFile = null;
	private String configName;

	public DataYML(String configName) {
		this.configName = configName;
	}

	public DataYML() {
		reloadCustomConfig();
	}

	public void reloadCustomConfig() {
		if (configFile == null) {
			configFile = new File(club.claycoffee.ClayTech.ClayTech.plugin.getDataFolder(), configName);
		}
		config = YamlConfiguration.loadConfiguration(configFile);

		Reader stream;
		try {
			stream = new InputStreamReader(club.claycoffee.ClayTech.ClayTech.plugin.getResource(configName), "UTF8");
			if (stream != null) {
				YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(stream);
				config.setDefaults(defConfig);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public FileConfiguration getCustomConfig() {
		if (config == null) {
			reloadCustomConfig();
		}
		return config;
	}

	public void saveCustomConfig() {
		if (config == null || configFile == null) {
			return;
		}
		try {
			getCustomConfig().save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveCDefaultConfig() {
		if (config == null) {
			configFile = new File(club.claycoffee.ClayTech.ClayTech.plugin.getDataFolder(), configName);
		}
		if (!configFile.exists()) {
			Bukkit.getLogger().info("§bInit.");
			club.claycoffee.ClayTech.ClayTech.plugin.saveResource(configName, false);
		}
	}
}
