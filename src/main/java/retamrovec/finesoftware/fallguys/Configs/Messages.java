package retamrovec.finesoftware.fallguys.Configs;

import me.clip.placeholderapi.libs.kyori.adventure.platform.facet.Facet;
import org.bukkit.ChatColor;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

public class Messages {

    public static void newConfiguration() {

        ConfigManager.getConfiguration().addDefault("player.join", ChatColor.translateAlternateColorCodes('&', "&9&l%player_name% &5has joined the game!"));
        ConfigManager.getConfiguration().addDefault("player.leave", ChatColor.translateAlternateColorCodes('&', "&9&l%player_name% &5has left the game!"));
        ConfigManager.getConfiguration().addDefault("player.qualified", ChatColor.translateAlternateColorCodes('&', "&aYou has been qualified in this round. &9Congratulations!"));
        ConfigManager.getConfiguration().addDefault("player.left_arena", ChatColor.translateAlternateColorCodes('&', "&aYou left the arena."));
        ConfigManager.getConfiguration().addDefault("player.join_arena", ChatColor.translateAlternateColorCodes('&', "&aYou joined the arena."));
        ConfigManager.getConfiguration().addDefault("game.countdown", ChatColor.translateAlternateColorCodes('&', "&aGame will start in %fallguys_countdown%&a seconds."));
        ConfigManager.getConfiguration().addDefault("game.start", ChatColor.translateAlternateColorCodes('&', "&aGame has started. Get to the finish line first to get qualified. Good luck!"));
        ConfigManager.getConfiguration().addDefault("game.end", ChatColor.translateAlternateColorCodes('&', "&9&l%player_name% &ahas won! Thanks for playing, hope you will come on this mini-game soon as possible!"));
        ConfigManager.getConfiguration().addDefault("game.short_players", ChatColor.translateAlternateColorCodes('&', "&aThere is not enough players. Countdown stopped."));
        ConfigManager.getConfiguration().addDefault("game.end_short_players", ChatColor.translateAlternateColorCodes('&', "&aThe game has ended as too many players have left."));
        ConfigManager.getConfiguration().addDefault("game.countdown.title", ChatColor.translateAlternateColorCodes('&', "&a%fallguys_countdown% seconds."));
        ConfigManager.getConfiguration().addDefault("game.countdown.subtitle", ChatColor.translateAlternateColorCodes('&', "&auntil game starts."));
        ConfigManager.getConfiguration().addDefault("game.available_arenas", ChatColor.translateAlternateColorCodes('&', "&aThese are the available arenas:"));
        ConfigManager.getConfiguration().addDefault("error.invalid_use", ChatColor.translateAlternateColorCodes('&', "&cInvalid use of command."));
        ConfigManager.getConfiguration().addDefault("error.player_is_not_in_arena", ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', "&cYou are not in an arena.")));
        ConfigManager.getConfiguration().addDefault("error.already_in_arena", ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', "&cYou are already in an arena." )));
        ConfigManager.getConfiguration().addDefault("error.invalid_arena", ChatColor.translateAlternateColorCodes('&', "&cYou specified an invalid arena ID.") );
        ConfigManager.getConfiguration().addDefault("error.game-started", ChatColor.translateAlternateColorCodes('&', "&cGame already started."));
        ConfigManager.getConfiguration().options().copyDefaults(true);
    }

}
