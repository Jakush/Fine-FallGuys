package retamrovec.finesoftware.fallguys.Managers;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import retamrovec.finesoftware.fallguys.FallGuys;

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

    public static void newFolder() {
        if (!FallGuys.instance().getDataFolder().exists()) {
            FallGuys.instance().getDataFolder().mkdirs();
        }
    }

}
