package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.FallGuys;
import yando0.finesoftware.fallguys.PAPI;

import java.util.HashMap;
import java.util.UUID;

public class Game implements LanguageHandler {

     /*

    This class is developed by RETAMROVEC.

     */

    private final Arena arena;
    private final Config config = new Config(FallGuys.instance());
    public final HashMap<UUID, Integer> levels = new HashMap<>();

    public Game(Arena arena) {
        this.arena = arena;
    }

    public void start() {
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("game.start"), true)));
        arena.teleport(config.getArenaSpawn(arena.getId()));

        for (UUID uuid : arena.getPlayers()) {
            levels.put(uuid, 0);
        }
    }

    public void qualify(@NotNull Player player, boolean spectator) {
        int playerLevel = levels.get(player.getUniqueId()) + 1;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("player.qualified"), player)));
        levels.replace(player.getUniqueId(), playerLevel);
        if (levels.get(player.getUniqueId()) == 2) {
            arena.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("game.end"), player)));
            arena.reset(true);
            return;
        }
        if (spectator) {
            player.setGameMode(GameMode.SPECTATOR);
        }
    }
}
