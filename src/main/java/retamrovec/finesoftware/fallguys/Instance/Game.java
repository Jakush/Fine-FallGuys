package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Managers.LevelManager;
import yando0.finesoftware.fallguys.PAPI;

import java.util.UUID;

public class Game implements LanguageHandler {

     /*

    This class is developed by RETAMROVEC.

     */

    private final Arena arena;

    public Game(Arena arena) {
        this.arena = arena;
    }

    public void start() {
        Config config = new Config(FallGuys.instance());
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("game.start"), true)));
        arena.teleport(config.getArenaSpawn(arena.getId()));

        for (UUID uuid : arena.getPlayers()) {
            new LevelManager(uuid);
        }
    }

    public void qualify(@NotNull Player player, boolean spectator) {
        LevelManager level = new LevelManager(player.getUniqueId());
        int playerLevel = level.getLevel() + 1;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("player.qualified"), player)));
        level.setLevel(playerLevel);
        if (playerLevel == 2) {
            arena.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("game.end"), player)));
            arena.reset(true);
            return;
        }
        if (spectator) {
            player.setGameMode(GameMode.SPECTATOR);
        }
    }

}
