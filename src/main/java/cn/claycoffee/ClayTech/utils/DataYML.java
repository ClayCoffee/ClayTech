package cn.claycoffee.ClayTech.utils;

import cn.claycoffee.ClayTech.ClayTech;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

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
            configFile = new File(ClayTech.getInstance().getDataFolder(), configName);
        }
        config = YamlConfiguration.loadConfiguration(configFile);

        Reader stream;
        try {
            stream = new InputStreamReader(ClayTech.getInstance().getResource(configName), "UTF8");
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
            configFile = new File(ClayTech.getInstance().getDataFolder(), configName);
        }
        if (!configFile.exists()) {
            Bukkit.getLogger().info("§bInit.");
            ClayTech.getInstance().saveResource(configName, false);
        }
    }
}
