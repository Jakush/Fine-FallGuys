package retamrovec.finesoftware.fallguys;

import boogeyman.finesoftware.fallguys.FallGuysBoogey;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import retamrovec.finesoftware.fallguys.Commands.ArenaCommand;
import retamrovec.finesoftware.fallguys.Managers.ArenaManager;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;
import retamrovec.finesoftware.fallguys.Configs.Messages;
import yando0.finesoftware.fallguys.FallGuysYando;
import yando0.finesoftware.fallguys.PAPI;

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

        arenaManager = new ArenaManager(this);
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PAPI(this, arenaManager).register();
        }
        getCommand("fallguys").setExecutor(new ArenaCommand());
    }

    @Override
    public void onDisable() {

    }

    public static FallGuys instance() {
        return mainInstance;
    }

    public ArenaManager getArenaManager() {return arenaManager;}


}
