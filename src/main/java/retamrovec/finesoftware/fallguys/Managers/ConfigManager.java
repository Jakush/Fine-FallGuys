package retamrovec.finesoftware.fallguys.Managers;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import retamrovec.finesoftware.fallguys.FallGuys;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private static File configuration;
    private static YamlConfiguration getConfig;
    public ConfigManager(File folder, String file) {
        ConfigManager.configuration = new File(folder, file);
        ConfigManager.getConfig = YamlConfiguration.loadConfiguration(configuration);
    }

    public static void createConfiguration() {
        if (!configuration.exists()) {
            try {
                configuration.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

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

    public static void createFolder() {
        if (!FallGuys.instance().getDataFolder().exists()) {
            FallGuys.instance().getDataFolder().mkdirs();
        }
    }

}
