package retamrovec.finesoftware.fallguys.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private static String file;
    private static File folder;
    public ConfigManager(String file, File folder) {
        ConfigManager.file = file;
        ConfigManager.folder = folder;
    }
    private static final File configuration = new File(folder, file);
    private static final YamlConfiguration getConfig = YamlConfiguration.loadConfiguration(configuration);

    public static YamlConfiguration getConfiguration() {
        return getConfig;
    }

    public static void saveConfiguration() {
        try {
            getConfig.save(configuration);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void reloadConfiguration() {
        try {
            getConfig.load(configuration);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void newConfiguration() {
        getConfig.addDefault("start.required_players", 2);
        getConfig.addDefault("start.countdown-seconds", 15);
        getConfig.addDefault("spawn.world", "world");
        getConfig.addDefault("spawn.x", "0");
        getConfig.addDefault("spawn.y", "64");
        getConfig.addDefault("spawn.z", "0");
        getConfig.addDefault("spawn.yaw", "0");
        getConfig.addDefault("spawn.pitch", "90");
        getConfig.options().copyDefaults(true);
    }

    public static int getNeededPlayers() {
        return ConfigManager.getConfiguration().getInt("start.required_players");
    }

    public static int getCountdown() {
        return ConfigManager.getConfiguration().getInt("start.countdown-seconds");
    }

    public static Location getSpawn() {
        return new Location(
            Bukkit.getWorld(getConfig.getString("spawn.world")),
            getConfig.getDouble("spawn.x"),
            getConfig.getDouble("spawn.y"),
            getConfig.getDouble("spawn.z"),
            (float) getConfig.getDouble("spawn.yaw"),
            (float) getConfig.getDouble("spawn.pitch"));
    }

}
