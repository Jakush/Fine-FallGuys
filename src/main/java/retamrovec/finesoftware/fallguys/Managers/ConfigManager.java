package retamrovec.finesoftware.fallguys.Managers;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import retamrovec.finesoftware.fallguys.FallGuys;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    /*

    This class is developed by RETAMROVEC.

     */

    private File configuration;
    private YamlConfiguration getConfig;
    public ConfigManager(File folder, String file) {
        this.configuration = new File(folder, file);
        this.getConfig = YamlConfiguration.loadConfiguration(configuration);
    }

    public YamlConfiguration getConfiguration() {
        return getConfig;
    }

    public void saveConfiguration() {
        try {
            getConfig.save(configuration);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reloadConfiguration() {
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
