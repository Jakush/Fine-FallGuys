package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;
import yando0.finesoftware.fallguys.PAPI;

import java.util.HashMap;
import java.util.UUID;

public class Game {

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
        new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("game.start"), true)));
        arena.teleport(Config.getArenaSpawn(arena.getId()));
        Bukkit.getLogger().info("ID" + arena.getId());
        Bukkit.getLogger().info("Location" + Config.getArenaSpawn(arena.getId()));

        for (UUID uuid : arena.getPlayers()) {
            levels.put(uuid, 0);
        }
    }

    public void qualify(@NotNull Player player) {
        new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
        int playerLevel = levels.get(player.getUniqueId()) + 1;
        if (playerLevel == 2) {
            arena.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("game.end"), player)));
            arena.reset(true);
            return;
        }

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("player.qualified"), player)));
        levels.replace(player.getUniqueId(), playerLevel);
    }

}
