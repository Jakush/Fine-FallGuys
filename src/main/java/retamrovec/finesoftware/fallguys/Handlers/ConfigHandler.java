package retamrovec.finesoftware.fallguys.Handlers;

import org.bukkit.configuration.file.YamlConfiguration;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

public interface ConfigHandler {

    /*

    This class is developed by RETAMROVEC.

     */

    default YamlConfiguration getConfig() {
        ConfigManager config = new ConfigManager(FallGuys.instance().getDataFolder(), "config.yml");
        return config.getConfiguration();
    }

    default void reloadConfig() {
        ConfigManager config = new ConfigManager(FallGuys.instance().getDataFolder(), "config.yml");
        config.reloadConfiguration();
    }

    default void saveConfig() {
        ConfigManager config = new ConfigManager(FallGuys.instance().getDataFolder(), "config.yml");
        config.saveConfiguration();
    }

    default void saveDefaultConfig() {
        ConfigManager config = new ConfigManager(FallGuys.instance().getDataFolder(), "config.yml");
        config.createConfiguration();
        Config.newConfiguration();
        config.saveConfiguration();
    }
}
