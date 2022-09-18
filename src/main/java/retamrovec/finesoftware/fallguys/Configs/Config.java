package retamrovec.finesoftware.fallguys.Configs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

public class Config implements ConfigHandler {

    /*

    This class is developed by RETAMROVEC.

     */

    public static void newConfiguration() {
        ConfigManager config = new ConfigManager(FallGuys.instance().getDataFolder(), "config.yml");
        config.getConfiguration().addDefault("required_players", 2);
        config.getConfiguration().addDefault("countdown-seconds", 15);
        config.getConfiguration().addDefault("spawn.world", "world");
        config.getConfiguration().addDefault("spawn.x", "0");
        config.getConfiguration().addDefault("spawn.y", "64");
        config.getConfiguration().addDefault("spawn.z", "0");
        config.getConfiguration().addDefault("spawn.yaw", "0");
        config.getConfiguration().addDefault("spawn.pitch", "90");
        config.getConfiguration().addDefault("arenas.0.world", "world");
        config.getConfiguration().addDefault("arenas.0.x", "16");
        config.getConfiguration().addDefault("arenas.0.y", "69");
        config.getConfiguration().addDefault("arenas.0.z", "32");
        config.getConfiguration().addDefault("arenas.0.yaw", "0");
        config.getConfiguration().addDefault("arenas.0.pitch", "90");
        config.getConfiguration().options().copyDefaults(true);
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
        return new Location(
                Bukkit.getWorld(getConfig().getString("arenas." + id + ".world")),
                getConfig().getDouble("arenas." + id + ".x"),
                getConfig().getDouble("arenas." + id + ".y"),
                getConfig().getDouble("arenas." + id + ".z"),
                (float) getConfig().getDouble("arenas." + id + ".yaw"),
                (float) getConfig().getDouble("arenas." + id + ".pitch"));
    }

    public void init() {
        saveDefaultConfig();
    }
}