package retamrovec.finesoftware.fallguys.Configs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;

/**
 * @author RETAMROVEC
 */
public class Config implements ConfigHandler {

    private static JavaPlugin plugin;
    public Config(JavaPlugin plugin) {
        Config.plugin = plugin;
    }

    public static void newConfiguration() {
        plugin.saveResource("config.yml", false);
    }

    public int getGameTime() {
        return getConfig().getInt("game-time");
    }

    public int getNeededPlayers() {
        return getConfig().getInt("required_players");
    }

    public int getCountdownSeconds() {
        return getConfig().getInt("countdown-seconds");
    }

    public Location getLobbySpawn() {
        return new Location(
                Bukkit.getWorld(getConfig().getString("spawn.world")),
                getConfig().getDouble("spawn.x"),
                getConfig().getDouble("spawn.y"),
                getConfig().getDouble("spawn.z"),
                (float) getConfig().getDouble("spawn.yaw"),
                (float) getConfig().getDouble("spawn.pitch"));
    }

    public Location getArenaSpawn(int id) {
        return new Location(Bukkit.getWorld(getConfig().getString("arenas." + id + ".world")),
                getConfig().getDouble("arenas." + id + ".x"),
                getConfig().getDouble("arenas." + id + ".y"),
                getConfig().getDouble("arenas." + id + ".z"),
                (float) getConfig().getDouble("arenas." + id + ".yaw"),
                (float) getConfig().getDouble("arenas." + id + ".pitch"));
    }

    public Location getMapSpawn(int id, int mapID) {
        return new Location(Bukkit.getWorld(getConfig().getString("arenas." + id + mapID + ".world")),
                getConfig().getDouble("arenas." + id + mapID + ".x"),
                getConfig().getDouble("arenas." + id + mapID + ".y"),
                getConfig().getDouble("arenas." + id + mapID + ".z"),
                (float) getConfig().getDouble("arenas." + id + mapID + ".yaw"),
                (float) getConfig().getDouble("arenas." + id + mapID + ".pitch"));
    }
    public void init() {
        saveDefaultConfig();
    }
}