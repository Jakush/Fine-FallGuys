package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;
import retamrovec.finesoftware.fallguys.Maps.Map1.SlimeJump;
import retamrovec.finesoftware.fallguys.PAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author RETAMROVEC
 */
public class Arena implements ConfigHandler, LanguageHandler {

    private int id;
    private Location spawn;
    private Config config;

    private GameState state;
    private List<UUID> players;
    private Countdown countdown;
    private Game game;
    public Arena(int id, Location spawn, Config config) {
        this.id = id;
        this.spawn = spawn;
        this.config = config;

        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
        this.countdown = new Countdown(this);
        this.game = new Game(this);
    }

    /*

    GAME

     */

    public void start() {
        Bukkit.getLogger().info("222");
        game.start();
    }

    public void reset(boolean kickPlayers) {
        if (kickPlayers) {
            for (UUID uuid : players) {
                Location loc = config.getLobbySpawn();
                Bukkit.getPlayer(uuid).teleport(loc);
            }
            players.clear();
        }
        sendTitle("", "");
        state = GameState.RECRUITING;
        countdown.cancel();
        countdown = new Countdown(this);
        game = new Game(this);
    }
    /*

    TOOLS

     */

    public void sendMessage(String message) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    public void setGamemode(GameMode gamemode) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).setGameMode(gamemode);
        }
    }

    public void sendScoreboard() {
        for (UUID uuid : players) {
            Scoreboard scoreboard = new Scoreboard(uuid);
            scoreboard.start();
        }
    }

    public void sendTablist() {
        for (UUID uuid : players) {
            Tablist tablist = new Tablist(uuid);
            tablist.start();
        }
    }

    public void sendTitle(String title, String subtitle) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendTitle(title, subtitle);
        }
    }

    public void teleport(Location location) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).teleport(location);
        }
    }

    /*

    PLAYERS

    */

    public void addPlayer(@NotNull Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);

        if (state.equals(GameState.RECRUITING)) {
            if (players.size() >= config.getNeededPlayers()) {
                countdown.start();
            }
        }
    }

    public void removePlayer(@NotNull Player player) {
        Config config = new Config(FallGuys.instance());
        players.remove(player.getUniqueId());
        player.teleport(config.getLobbySpawn());
        player.sendTitle("", "");

        if (state == GameState.COUNTDOWN && players.size() < config.getNeededPlayers()) {
            new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
            sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getConfig().getString("game.short_players"), true)));
            reset(false);
            return;
        }

        if (state == GameState.LIVE && players.size() < config.getNeededPlayers()) {
            new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
            sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getConfig().getString("game.end_short_players"), true)));
            reset(false);
        }
    }

    public void kickPlayer(@NotNull Player player) {
        Config config = new Config(FallGuys.instance());
        players.remove(player.getUniqueId());
        player.teleport(config.getLobbySpawn());
        player.sendTitle("", "");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getConfig().getString("player.disqualified"), player)));
    }
    /*

    INFO

    */

    public int getId() {return id;}
    public GameState getState() {return state;}
    public List<UUID> getPlayers() {return players;}
    public Game getGame() {return game;}
    public void setState(GameState state) {this.state = state;}
    public List<SlimeJump> getBlocks() {return game.functionManager.getSlimeJumps();}
    public World getWorld() {return Bukkit.getWorld(getConfig().getString("arenas." + id + ".world"));}

}
