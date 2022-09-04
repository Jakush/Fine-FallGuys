package retamrovec.finesoftware.fallguys;

import org.bukkit.plugin.java.JavaPlugin;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

public class FallGuys extends JavaPlugin {

    private static FallGuys mainInstance;

    @Override
    public void onEnable() {
        yando0.finesoftware.fallguys.FallGuys yando0FallGuys = new yando0.finesoftware.fallguys.FallGuys();
        boogeyman.finesoftware.fallguys.FallGuys boogeymanFallGuys = new boogeyman.finesoftware.fallguys.FallGuys();
        mainInstance = this;
        yando0FallGuys.init();
        boogeymanFallGuys.init();
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
