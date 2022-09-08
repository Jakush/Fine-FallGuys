package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;
import yando0.finesoftware.fallguys.PAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    /*

    This class is developed by RETAMROVEC.

     */

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

        Bukkit.getLogger().info("" + players.size());
        Bukkit.getLogger().info("" + config.getNeededPlayers());

        if (state.equals(GameState.RECRUITING)) {
            if (players.size() >= config.getNeededPlayers()) {
                countdown.start();
            }
        }
    }

    public void removePlayer(@NotNull Player player) {
        players.remove(player.getUniqueId());
        player.teleport(Config.getLobbySpawn());
        player.sendTitle("", "");

        if (state == GameState.COUNTDOWN && players.size() < config.getNeededPlayers()) {
            new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
            sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("game.short_players"), true)));
            reset(false);
            return;
        }

        if (state == GameState.LIVE && players.size() < config.getNeededPlayers()) {
            new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
            sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("game.end_short_players"), true)));
            reset(false);
        }
    }

    /*

    INFO

    */

    public int getId() {return id;}
    public GameState getState() {return state;}
    public List<UUID> getPlayers() {return players;}
    public Game getGame() {return game;}
    public void setState(GameState state) {this.state = state;}

}
