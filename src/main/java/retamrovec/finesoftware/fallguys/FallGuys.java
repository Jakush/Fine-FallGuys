package retamrovec.finesoftware.fallguys;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import retamrovec.finesoftware.fallguys.Commands.ArenaCommand;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Configs.Functions;
import retamrovec.finesoftware.fallguys.Configs.Messages;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.Instance.Arena;
import retamrovec.finesoftware.fallguys.Listeners.*;
import retamrovec.finesoftware.fallguys.Managers.ArenaManager;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

import java.util.UUID;

public class FallGuys extends JavaPlugin implements LanguageHandler {

    // Managers (instances)
    private static FallGuys mainInstance;
    private PAPI papi;
    private ArenaManager arenaManager;
    // Values
    private boolean ownTAB = true;
    private String ver = getDescription().getVersion();

    @Override
    public void onLoad() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9|----------|"));
        Bukkit.getConsoleSender().sendMessage("    ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9| FallGuys "));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9| Plugin   "));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9| was      "));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9| proccesed"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9| to load!"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9| " + ver));
        Bukkit.getConsoleSender().sendMessage("    ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9|----------|"));
    }

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
            papi = new PAPI();
            papi.register();
        }
        if (Bukkit.getPluginManager().getPlugin("TAB") != null) {
            Plugin tab = Bukkit.getPluginManager().getPlugin("TAB");
            if (tab.getDescription().getAuthors().contains("NEZNAMY")) {
                setOwnTAB(false);
            }
        }
        Bukkit.getLogger().info(String.valueOf(getOwnTAB()));
        Bukkit.getPluginManager().registerEvents(new PlayerMoveOnSlime(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveOnIce(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(config), this);
        Bukkit.getPluginManager().registerEvents(new PlayerBlockBreak(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerBlockPlace(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLeaveListener(), this);
        Bukkit.getPluginManager().registerEvents(new GameEnd(), this);
        getCommand("fallguys").setExecutor(new ArenaCommand());
    }

    @Override
    public void onDisable() {
        removeScoreboard();
        removeTablist();
        for (Arena arena : arenaManager.getArenas()) {
            for (int i = 0; i < arena.getBlocks().size(); i++) {
                for (int i2 = 0; i2 < arena.getBlocks().get(i).blocks.size(); i2++) {
                    arena.getWorld().getBlockAt(arena.getBlocks().get(i).blocks.get(i2)).setType(Material.AIR);
                }
            }
        }
    }

    public void removeScoreboard() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (FallGuys.instance().getArenaManager().getArena(player) == null) return;
            if (FallGuys.instance().getArenaManager().getArena(player).getPlayers() == null) return;
            if (getArenaManager().getArena(player).getState() != GameState.LIVE) return;
            player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
            for (String s : getConfig().getStringList("scoreboard")) {
                player.getScoreboard().resetScores(ChatColor.translateAlternateColorCodes('&', PAPI.use(s, player)));
            }
        }
    }

    public void removeTablist() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (FallGuys.instance().getArenaManager().getArena(player) == null) return;
            if (FallGuys.instance().getArenaManager().getArena(player).getPlayers() == null) return;
            if (getArenaManager().getArena(player).getState() != GameState.LIVE) return;
            player.setPlayerListHeaderFooter(null, null);
        }
    }

    public static FallGuys instance() {
        return mainInstance;
    }

    public ArenaManager getArenaManager() {return arenaManager;}
    public PAPI getPAPI() {return papi;}

    public boolean getOwnTAB() {
        return ownTAB;
    }

    public void setOwnTAB(boolean ownTAB) {
        this.ownTAB = ownTAB;
    }

}
