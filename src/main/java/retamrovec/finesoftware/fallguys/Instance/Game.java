package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;
import yando0.finesoftware.fallguys.PAPI;

import java.util.HashMap;
import java.util.UUID;

public class Game {

    private Arena arena;
    private HashMap<UUID, Integer> levels;
    public Game(Arena arena) {
        this.arena = arena;
        levels = new HashMap<>();
    }

    public void start() {
        new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("game.start"), null)));

        for (UUID uuid : arena.getPlayers()) {
            levels.put(uuid, 0);
        }
    }

    public void addLevel(Player player) {
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
