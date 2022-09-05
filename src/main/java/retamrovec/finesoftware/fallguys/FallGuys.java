package retamrovec.finesoftware.fallguys;

import boogeyman.finesoftware.fallguys.FallGuysBoogey;
import org.bukkit.plugin.java.JavaPlugin;
import retamrovec.finesoftware.fallguys.Managers.ArenaManager;
import retamrovec.finesoftware.fallguys.Managers.Config;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;
import retamrovec.finesoftware.fallguys.Managers.Messages;
import yando0.finesoftware.fallguys.FallGuysYando;

public class FallGuys extends JavaPlugin {

    private static FallGuys mainInstance;
    private ArenaManager arenaManager;
    @Override
    public void onEnable() {
        FallGuysYando fallGuysYando = new FallGuysYando();
        FallGuysBoogey fallGuysBoogey = new FallGuysBoogey();
        mainInstance = this;
        fallGuysYando.init();
        fallGuysBoogey.init();
        ConfigManager.createFolder();
        new ConfigManager(getDataFolder(), "config.yml");
        ConfigManager.createConfiguration();
        Config.newConfiguration();
        ConfigManager.saveConfiguration();
        new ConfigManager(getDataFolder(), "messages.yml");
        ConfigManager.createConfiguration();
        Messages.newConfiguration();
        ConfigManager.saveConfiguration();

        new ArenaManager(this);
    }

    @Override
    public void onDisable() {

    }

    public static FallGuys instance() {
        return mainInstance;
    }

    public ArenaManager getArenaManager() {return arenaManager;}


}
