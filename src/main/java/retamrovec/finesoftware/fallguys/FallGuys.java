package retamrovec.finesoftware.fallguys;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import retamrovec.finesoftware.fallguys.Commands.ArenaCommand;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Configs.Functions;
import retamrovec.finesoftware.fallguys.Configs.Messages;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.Listeners.*;
import retamrovec.finesoftware.fallguys.Managers.ArenaManager;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

public class FallGuys extends JavaPlugin implements LanguageHandler {

    // Managers (instances)
    private static FallGuys mainInstance;
    private ArenaManager arenaManager;
    // Values
    private boolean ownTAB = true;

    @Override
    public void onEnable() {
        mainInstance = this;
        ConfigManager.createFolder();
        Config config = new Config(this);
        config.init();
        Messages messages = new Messages(this);
        messages.init();
        Functions functions = new Functions(this);
        functions.init();
        arenaManager = new ArenaManager(new Config(FallGuys.instance()));
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PAPI(this, arenaManager).register();
        }
        if (Bukkit.getPluginManager().getPlugin("TAB") != null) {
            Plugin tab = Bukkit.getPluginManager().getPlugin("TAB");
            if (tab.getDescription().getAuthors().contains("NEZNAMY")) {
                setOwnTAB(false);
            }
        }
        Bukkit.getPluginManager().registerEvents(new PlayerMoveOnSlime(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveOnIce(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(config), this);
        Bukkit.getPluginManager().registerEvents(new PlayerBlockBreak(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerBlockPlace(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLeaveListener(), this);
        getCommand("fallguys").setExecutor(new ArenaCommand());
    }

    @Override
    public void onDisable() {

    }

    public static FallGuys instance() {
        return mainInstance;
    }

    public ArenaManager getArenaManager() {return arenaManager;}

    public boolean getOwnTAB() {
        return ownTAB;
    }

    public void setOwnTAB(boolean ownTAB) {
        this.ownTAB = ownTAB;
    }

}
