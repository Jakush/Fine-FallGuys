package retamrovec.finesoftware.fallguys.Configs;

import org.bukkit.ChatColor;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

public class Messages {

    public static void newConfiguration() {
        ConfigManager.getConfiguration().addDefault("player.join", ChatColor.translateAlternateColorCodes('&', "&9&l%player_name% &5has joined the game!"));
        ConfigManager.getConfiguration().addDefault("player.leave", ChatColor.translateAlternateColorCodes('&', "&9&l%player_name% &5has left the game!"));
        ConfigManager.getConfiguration().addDefault("game.countdown", ChatColor.translateAlternateColorCodes('&', "&aGame will start in %fallguys_countdown%&a seconds."));
        ConfigManager.getConfiguration().options().copyDefaults(true);
    }

}
