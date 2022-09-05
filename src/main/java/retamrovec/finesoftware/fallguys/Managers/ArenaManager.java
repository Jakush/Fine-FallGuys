package retamrovec.finesoftware.fallguys.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Instance.Arena;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    private List<Arena> arenas = new ArrayList<>();

    public ArenaManager(FallGuys fallGuys) {
        new ConfigManager(fallGuys.getDataFolder(), "config.yml");
        for (String str : ConfigManager.getConfiguration().getConfigurationSection("arenas.").getKeys(false)) {
            arenas.add(new Arena(Integer.parseInt(str), new Location(
                    Bukkit.getWorld(ConfigManager.getConfiguration().getString("arenas." + str + ".world")),
                    ConfigManager.getConfiguration().getDouble("arenas." + str + ".x"),
                    ConfigManager.getConfiguration().getDouble("arenas." + str + ".y"),
                    ConfigManager.getConfiguration().getDouble("arenas." + str + ".z"),
                    (float) ConfigManager.getConfiguration().getDouble("arenas." + str + ".yaw"),
                    (float) ConfigManager.getConfiguration().getDouble("arenas." + str + ".pitch"))));
        }
    }

    public List<Arena> getArenas() {return arenas;}

    public Arena getArena(Player player) {
        for (Arena arena : arenas) {
            if (arena.getPlayers().contains(player.getUniqueId())) {
                return arena;
            }
        }
        return null;
    }

    public Arena getArena(int id) {
        for (Arena arena : arenas) {
            if (arena.getId() == id) {
                return arena;
            }
        }
        return null;
    }

}
