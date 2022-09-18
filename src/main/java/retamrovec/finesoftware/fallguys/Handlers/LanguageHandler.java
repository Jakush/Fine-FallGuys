package retamrovec.finesoftware.fallguys.Handlers;

import org.bukkit.configuration.file.YamlConfiguration;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Configs.Messages;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

import java.io.File;

public interface LanguageHandler {

    /*

    This class is developed by RETAMROVEC.

     */

    default YamlConfiguration getLang() {
        ConfigManager config = new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
        return config.getConfiguration();
    }

    default void reloadLang() {
        ConfigManager config = new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
        config.reloadConfiguration();
    }

    default void saveLang() {
        ConfigManager config = new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
        config.saveConfiguration();
    }

    default void saveDefaultLang() {
        File file = new File(FallGuys.instance().getDataFolder(), "messages.yml");
        if (!file.exists()) {
            Messages.newConfiguration();
        }
    }
}
