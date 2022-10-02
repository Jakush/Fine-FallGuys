package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Handlers.FunctionsHandler;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.Managers.FunctionManager;
import retamrovec.finesoftware.fallguys.PAPI;

import java.util.HashMap;
import java.util.UUID;

/**
 *
 * You don't have permission to copy this class.
 * @author RETAMROVEC
 *
 */
public class Game implements LanguageHandler, FunctionsHandler {

    private final Arena arena;
    private final FunctionManager functionManager;
    private final Config config = new Config(FallGuys.instance());
    public final HashMap<UUID, Integer> levels = new HashMap<>();

    public Game(Arena arena) {
        this.arena = arena;
        this.functionManager = new FunctionManager(arena.getId());
    }

    public void start() {
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("game.start"), true)));
        arena.teleport(config.getArenaSpawn(arena.getId()));

        /*

        LEVELS

         */


        for (UUID uuid : arena.getPlayers()) {
            levels.put(uuid, 0);
        }

        /*

        FUNCTIONS IN-GAME

         */

        functionManager.startAll();
    }

    public void qualify(@NotNull Player player, boolean spectator) {
        int playerLevel = levels.get(player.getUniqueId()) + 1;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("player.qualified"), player)));
        levels.replace(player.getUniqueId(), playerLevel);
        if (levels.get(player.getUniqueId()) == 2) {
            arena.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("game.end"), player)));
            functionManager.startAll();
            arena.reset(true);
            return;
        }
        if (spectator) {
            player.setGameMode(GameMode.SPECTATOR);
        }
    }
}
