package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.Managers.Config;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

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

    public void addPlayer(@NotNull Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);
    }

    public void removePlayer(@NotNull Player player) {
        players.add(player.getUniqueId());
        player.teleport(Config.getLobbySpawn());
    }

    public int getId() {return id;}
    public GameState getState() {return state;}
    public List<UUID> getPlayers() {return players;}

}
