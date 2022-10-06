package retamrovec.finesoftware.fallguys;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import retamrovec.finesoftware.fallguys.API.FallGuysAPI;
import retamrovec.finesoftware.fallguys.Commands.ArenaCommand;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Configs.Functions;
import retamrovec.finesoftware.fallguys.Configs.Messages;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.Instance.Arena;
import retamrovec.finesoftware.fallguys.Listeners.*;
import retamrovec.finesoftware.fallguys.Managers.ArenaManager;
import retamrovec.finesoftware.fallguys.Managers.BungeeManager;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

import java.util.UUID;

public class FallGuys extends JavaPlugin implements LanguageHandler {

    // Managers (instances)
    private static FallGuys mainInstance;
    private PAPI papi;
    private ArenaManager arenaManager;
    // Values
    private boolean ownTAB = true;
    private int dependTotal;
    private int commandsTotal;
    private int listenersTotal;
    private String ver = getDescription().getVersion();

    @Override
    public void onLoad() {
        logConsole("&9|----------|");
        logConsole("&9|");
        logConsole("&9| &cFALL GUYS");
        logConsole("&9| &7Loading plugin version");
        logConsole("&9| &a" + ver);
        logConsole("&9|");
        logConsole("&9| &7Loading server version");
        logConsole("&9| &a" + Bukkit.getServer().getBukkitVersion());
        logConsole("&9| &a" + Bukkit.getServer().getVersion());
        logConsole("&9|");
        logConsole("&9|----------|");
    }

    @Override
    public void onEnable() {
        logConsole("&9|----------|");
        logConsole("&9|");
        logConsole("&9| &cFALL GUYS");
        mainInstance = this;
        logConsole("&9| &7Enabling plugin version");
        logConsole("&9| &a" + ver);
        logConsole("&9|");
        logConsole("&9| &7Loading configs...");
        ConfigManager.createFolder();
        Config config = new Config(this);
        config.init();
        Messages messages = new Messages(this);
        messages.init();
        Functions functions = new Functions(this);
        functions.init();
        logConsole("&9| &7Loading arenas...");
        arenaManager = new ArenaManager(new Config(FallGuys.instance()));
        logConsole("&9| &7Checking for dependencies...");
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            logConsole("&9| &7PlaceholderAPI was found!");
            logConsole("&9| &7Hooking into PlaceholderAPI.");
            papi = new PAPI();
            papi.register();
            dependTotal++;
        }
        if (Bukkit.getPluginManager().getPlugin("TAB") != null) {
            logConsole("&9| &7TAB plugin was found!");
            logConsole("&9| &7Loading TAB (unknown author)...");
            Plugin tab = Bukkit.getPluginManager().getPlugin("TAB");
            if (tab.getDescription().getAuthors().contains("NEZNAMY")) {
                logConsole("&9| &7Hooking into TAB (NEZNAMY).");
                setOwnTAB(false);
                dependTotal++;
            }
            else {
                logConsole("&9| &7TAB plugin author is unknown.");
                logConsole("&9| &7TAB plugin hook was cancelled.");
                logConsole("&9| &7Using FallGuys tablist.");
            }
        }
        logConsole("&9| &7Total of &a" + dependTotal + " &7dependencies were found and loaded!");
        logConsole("&9| &7Loading commands...");
        loadCommand("fallguys", new ArenaCommand());
        logConsole("&9| &7Loaded &a" + commandsTotal + " &7command(s)!");
        logConsole("&9| &7Loading listeners...");
        logConsole("&9| &7Loading PlayerMovement listeners...");
        loadListener(new PlayerMoveOnSlime());
        loadListener(new PlayerMoveOnIce());
        loadListener(new PlayerMoveListener(config));
        logConsole("&9| &7Loading PlayerBlockBreak and PlayerBlockPlace listeners...");
        loadListener(new PlayerBlockBreak());
        loadListener(new PlayerBlockPlace());
        logConsole("&9| &7Loading PlayerJoin and PlayerLeave listeners...");
        loadListener(new PlayerLeaveListener());
        loadListener(new PlayerJoinListener());
        logConsole("&9| &7Loading Game listeners...");
        logConsole("&9| &7Loaded &a" + listenersTotal + " &7listener(s)!");
        if (getConfig().getBoolean("bungeecord")) {
            logConsole("&9| &7Enabling bungeecord features...");
            BungeeManager bungee = new BungeeManager(this);
            bungee.registerBungeecord();
        }
        logConsole("&9|");
        logConsole("&9|----------|");
    }

    @Override
    public void onDisable() {
        logConsole("&9|----------|");
        logConsole("&9|");
        logConsole("&9| &cFALL GUYS");
        logConsole("&9| &7Disabling plugin version");
        logConsole("&9| &a" + ver);
        logConsole("&9|");
        logConsole("&9| &7Removing tablist from players...");
        removeTablist();
        logConsole("&9| &7Removing scoreboard from players...");
        removeScoreboard();
        logConsole("&9| &7Removing all blocks from plugin...");
        for (Arena arena : arenaManager.getArenas()) {
            for (int i = 0; i < arena.getBlocks().size(); i++) {
                for (int i2 = 0; i2 < arena.getBlocks().get(i).blocks.size(); i2++) {
                    arena.getWorld().getBlockAt(arena.getBlocks().get(i).blocks.get(i2)).setType(Material.AIR);
                }
            }
        }
        if (getConfig().getBoolean("bungeecord")) {
            logConsole("&9| &7Disabling bungeecord features...");
            BungeeManager bungee = new BungeeManager(this);
            bungee.unregisterBungeecord();
        }
        logConsole("&9|");
        logConsole("&9|----------|");
    }

    public void loadCommand(String command, CommandExecutor executor) {
    getCommand(command).setExecutor(executor);
        commandsTotal++;
    }

    public void loadListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
        listenersTotal++;
    }

    public void logConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
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
