package retamrovec.finesoftware.fallguys.Handlers;

import org.bukkit.configuration.file.YamlConfiguration;
import retamrovec.finesoftware.fallguys.Configs.Functions;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

import java.io.File;

public interface FunctionsHandler {

    /*

    This class is developed by RETAMROVEC.

     */

    default YamlConfiguration getFunctions() {
        ConfigManager config = new ConfigManager(FallGuys.instance().getDataFolder(), "functions.yml");
        return config.getConfiguration();
    }

    default void reloadFunctions() {
        ConfigManager config = new ConfigManager(FallGuys.instance().getDataFolder(), "functions.yml");
        config.reloadConfiguration();
    }

    default void saveFunctions() {
        ConfigManager config = new ConfigManager(FallGuys.instance().getDataFolder(), "functions.yml");
        config.saveConfiguration();
    }

    default void saveDefaultFunctions() {
        File file = new File(FallGuys.instance().getDataFolder(), "functions.yml");
        if (!file.exists()) {
            Functions.newConfiguration();
        }
    }
}
