package retamrovec.finesoftware.fallguys.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;
import retamrovec.finesoftware.fallguys.Instance.Arena;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RETAMROVEC
 */
public class ArenaManager implements ConfigHandler {

    private List<Arena> arenas = new ArrayList<>();

    public ArenaManager(Config config) {
        for (String str : getConfig().getConfigurationSection("arenas.").getKeys(false)) {
            arenas.add(new Arena(Integer.parseInt(str), new Location(
                    Bukkit.getWorld(getConfig().getString("arenas." + str + ".world")),
                    getConfig().getDouble("arenas." + str + ".x"),
                    getConfig().getDouble("arenas." + str + ".y"),
                    getConfig().getDouble("arenas." + str + ".z"),
                    (float) getConfig().getDouble("arenas." + str + ".yaw"),
                    (float) getConfig().getDouble("arenas." + str + ".pitch")), config));
        }
    }

    public List<Arena> getArenas() {return arenas;}

    public Arena getArena(OfflinePlayer player, boolean understandUse) {
        if (!understandUse) {
            Bukkit.getLogger().severe("You must understand this method and make it true.");
            Bukkit.getLogger().severe("For more information ask RETAMROVEC.");
            return null;
        }
        for (Arena arena : arenas) {
            if (arena.getPlayers().contains(player.getUniqueId())) {
                return arena;
            }
        }
        return null;
    }

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
