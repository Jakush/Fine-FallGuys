package retamrovec.finesoftware.fallguys;

import boogeyman.finesoftware.fallguys.FallGuysBoogey;
import org.bukkit.plugin.java.JavaPlugin;
import retamrovec.finesoftware.fallguys.Managers.Config;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;
import retamrovec.finesoftware.fallguys.Managers.Messages;
import yando0.finesoftware.fallguys.FallGuysYando;

public class FallGuys extends JavaPlugin {

    private static FallGuys mainInstance;

    @Override
    public void onEnable() {
        FallGuysYando fallGuysYando = new FallGuysYando();
        FallGuysBoogey fallGuysBoogey = new FallGuysBoogey();
        mainInstance = this;
        fallGuysYando.init();
        fallGuysBoogey.init();
        ConfigManager.newFolder();
        new ConfigManager("config.yml", this.getDataFolder());
        Config.newConfiguration();
        ConfigManager.saveConfiguration();
        new ConfigManager("messages.yml", this.getDataFolder());
        Messages.newConfiguration();
        ConfigManager.saveConfiguration();
    }

    @Override
    public void onDisable() {

    }

    public static FallGuys instance() {
        return mainInstance;
    }


}
