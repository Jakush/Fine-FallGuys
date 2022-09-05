package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    /*

    This class is developed by RETAMROVEC.

     */

    private int id;
    private Location spawn;

    private GameState state;
    private List<UUID> players;
    private Countdown countdown;
    private Game game;
    public Arena(int id, Location spawn) {
        this.id = id;
        this.spawn = spawn;

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
                Location loc = Config.getLobbySpawn();
                Bukkit.getPlayer(uuid).teleport(loc);
            }
            players.clear();
        }
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

    /*

    PLAYERS

    */

    public void addPlayer(@NotNull Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);

        if (state.equals(GameState.RECRUITING) && players.size() >= Config.getNeededPlayers()) {
            countdown.start();
        }
    }

    public void removePlayer(@NotNull Player player) {
        players.add(player.getUniqueId());
        player.teleport(Config.getLobbySpawn());
    }

    /*

    INFO

    */

    public int getId() {return id;}
    public GameState getState() {return state;}
    public List<UUID> getPlayers() {return players;}
    public void setState(GameState state) {this.state = state;}

}
