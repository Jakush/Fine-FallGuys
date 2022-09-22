package retamrovec.finesoftware.fallguys;

import boogeyman.finesoftware.fallguys.FallGuysBoogey;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import retamrovec.finesoftware.fallguys.Commands.ArenaCommand;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Configs.Functions;
import retamrovec.finesoftware.fallguys.Configs.Messages;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.Listeners.*;
import retamrovec.finesoftware.fallguys.Managers.ArenaManager;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;
import yando0.finesoftware.fallguys.FallGuysYando;
import yando0.finesoftware.fallguys.PAPI;

public class FallGuys extends JavaPlugin implements LanguageHandler {

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
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(config), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveOnSlime(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveOnIce(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerBlockBreak(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerBlockPlace(), this);
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
