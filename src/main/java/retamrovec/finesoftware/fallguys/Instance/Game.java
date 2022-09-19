package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.ChatColor;
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

    private static Arena arena;
    private static HashMap<UUID, Integer> levels;
    public Game(Arena arena) {
        Game.arena = arena;
        levels = new HashMap<>();
    }

    public void start() {
        Config config = new Config(FallGuys.instance());
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("game.start"), true)));
        arena.teleport(config.getArenaSpawn(arena.getId()));

        for (UUID uuid : arena.getPlayers()) {
            levels.put(uuid, 0);
        }
    }

    public void qualify(@NotNull Player player) {
        int playerLevel = levels.get(player.getUniqueId()) + 1;
        if (playerLevel == 2) {
            arena.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("game.end"), player)));
            arena.reset(true);
            return;
        }

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("player.qualified"), player)));
        levels.replace(player.getUniqueId(), playerLevel);
    }

}
