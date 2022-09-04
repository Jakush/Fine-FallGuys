package retamrovec.finesoftware.fallguys.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Config {

    public static int getNeededPlayers() {
        return ConfigManager.getConfiguration().getInt("start.required_players");
    }

    public static int getCountdown() {
        return ConfigManager.getConfiguration().getInt("start.countdown-seconds");
    }

    public static Location getSpawn() {
        return new Location(
                Bukkit.getWorld(ConfigManager.getConfiguration().getString("spawn.world")),
                ConfigManager.getConfiguration().getDouble("spawn.x"),
                ConfigManager.getConfiguration().getDouble("spawn.y"),
                ConfigManager.getConfiguration().getDouble("spawn.z"),
                (float) ConfigManager.getConfiguration().getDouble("spawn.yaw"),
                (float) ConfigManager.getConfiguration().getDouble("spawn.pitch"));
    }

    public static void newConfiguration() {
        ConfigManager.getConfiguration().addDefault("start.required_players", 2);
        ConfigManager.getConfiguration().addDefault("start.countdown-seconds", 15);
        ConfigManager.getConfiguration().addDefault("spawn.world", "world");
        ConfigManager.getConfiguration().addDefault("spawn.x", "0");
        ConfigManager.getConfiguration().addDefault("spawn.y", "64");
        ConfigManager.getConfiguration().addDefault("spawn.z", "0");
        ConfigManager.getConfiguration().addDefault("spawn.yaw", "0");
        ConfigManager.getConfiguration().addDefault("spawn.pitch", "90");
        ConfigManager.getConfiguration().options().copyDefaults(true);
    }

}
