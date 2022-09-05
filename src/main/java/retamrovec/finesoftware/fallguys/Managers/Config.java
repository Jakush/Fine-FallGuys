package retamrovec.finesoftware.fallguys.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Config {

    public static void newConfiguration() {
        ConfigManager.getConfiguration().addDefault("required_players", 2);
        ConfigManager.getConfiguration().addDefault("countdown-seconds", 15);
        ConfigManager.getConfiguration().addDefault("spawn.world", "world");
        ConfigManager.getConfiguration().addDefault("spawn.x", "0");
        ConfigManager.getConfiguration().addDefault("spawn.y", "64");
        ConfigManager.getConfiguration().addDefault("spawn.z", "0");
        ConfigManager.getConfiguration().addDefault("spawn.yaw", "0");
        ConfigManager.getConfiguration().addDefault("spawn.pitch", "90");
        ConfigManager.getConfiguration().options().copyDefaults(true);
    }

    public static int getNeededPlayers() {
        return ConfigManager.getConfiguration().getInt("start.required_players");
    }

    public static int getCountdownSeconds() {
        return ConfigManager.getConfiguration().getInt("start.countdown-seconds");
    }

    public static Location getLobbySpawn() {
        return new Location(
                Bukkit.getWorld(ConfigManager.getConfiguration().getString("spawn.world")),
                ConfigManager.getConfiguration().getDouble("spawn.x"),
                ConfigManager.getConfiguration().getDouble("spawn.y"),
                ConfigManager.getConfiguration().getDouble("spawn.z"),
                (float) ConfigManager.getConfiguration().getDouble("spawn.yaw"),
                (float) ConfigManager.getConfiguration().getDouble("spawn.pitch"));
    }

}
