package retamrovec.finesoftware.fallguys.Configs;

import org.bukkit.ChatColor;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

public class Messages {

    public static void newConfiguration() {
        ConfigManager.getConfiguration().addDefault("player.join", "&9&l%player_name% &5has joined the game!");
        ConfigManager.getConfiguration().addDefault("player.leave", "&9&l%player_name% &5has left the game!");
        ConfigManager.getConfiguration().addDefault("player.qualified", "&aYou has been qualified in this round. &9Congratulations!");
        ConfigManager.getConfiguration().addDefault("player.left_arena", "&aYou left the arena.");
        ConfigManager.getConfiguration().addDefault("player.join_arena", "&aYou joined the arena.");
        ConfigManager.getConfiguration().addDefault("game.countdown", "&aGame will start in %fallguys_countdown%&a seconds.");
        ConfigManager.getConfiguration().addDefault("game.start", "&aGame has started. Get to the finish line first to get qualified. Good luck!");
        ConfigManager.getConfiguration().addDefault("game.end", "&9&l%player_name% &ahas won! Thanks for playing, hope you will come on this mini-game soon as possible!");
        ConfigManager.getConfiguration().addDefault("game.short_players", "&aThere is not enough players. Countdown stopped.");
        ConfigManager.getConfiguration().addDefault("game.end_short_players", "&aThe game has ended as too many players have left.");
        ConfigManager.getConfiguration().addDefault("game.countdown.title", "&a%fallguys_countdown% seconds.");
        ConfigManager.getConfiguration().addDefault("game.countdown.subtitle", "&auntil game starts.");
        ConfigManager.getConfiguration().addDefault("game.available_arenas", "&aThese are the available arenas:");
        ConfigManager.getConfiguration().addDefault("error.invalid_use", "&cInvalid use of command.");
        ConfigManager.getConfiguration().addDefault("error.player_is_not_in_arena", "&cYou are not in an arena.");
        ConfigManager.getConfiguration().addDefault("error.already_in_arena", "&cYou are already in an arena.");
        ConfigManager.getConfiguration().addDefault("error.invalid_arena", "&cYou specified an invalid arena ID.");
        ConfigManager.getConfiguration().addDefault("error.game-started", "&cGame already started.");
        ConfigManager.getConfiguration().options().copyDefaults(true);
    }

}
