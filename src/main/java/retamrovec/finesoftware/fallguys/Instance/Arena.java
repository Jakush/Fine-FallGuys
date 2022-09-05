package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.Configs.Config;

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
    public Arena(int id, Location spawn) {
        this.id = id;
        this.spawn = spawn;

        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
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
