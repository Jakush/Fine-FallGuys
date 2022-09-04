package retamrovec.finesoftware.fallguys;

import org.bukkit.plugin.java.JavaPlugin;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

public class FallGuys extends JavaPlugin {

    private static FallGuys mainInstance;

    @Override
    public void onEnable() {
        mainInstance = this;
        new ConfigManager("config.yml", this.getDataFolder());
        ConfigManager.newConfiguration();
        ConfigManager.saveConfiguration();
    }

    @Override
    public void onDisable() {

    }

    public static FallGuys instance() {
        return mainInstance;
    }


}
