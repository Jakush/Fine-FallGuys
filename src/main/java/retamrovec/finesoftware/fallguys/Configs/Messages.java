package retamrovec.finesoftware.fallguys.Configs;

import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

public class Messages implements LanguageHandler {

    /*

    This class is developed by RETAMROVEC.

     */

    public static void newConfiguration() {
        ConfigManager config = new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
        config.getConfiguration().addDefault("player.join", "&9&l%player_name% &5has joined the game!");
        config.getConfiguration().addDefault("player.leave", "&9&l%player_name% &5has left the game!");
        config.getConfiguration().addDefault("player.qualified", "&aYou has been qualified in this round. &9Congratulations!");
        config.getConfiguration().addDefault("player.left_arena", "&aYou left the arena.");
        config.getConfiguration().addDefault("player.join_arena", "&aYou joined the arena.");
        config.getConfiguration().addDefault("game.countdown", "&aGame will start in %fallguys_countdown%&a seconds.");
        config.getConfiguration().addDefault("game.start", "&aGame has started. Get to the finish line first to get qualified. Good luck!");
        config.getConfiguration().addDefault("game.end", "&9&l%player_name% &ahas won! Thanks for playing, hope you will come on this mini-game soon as possible!");
        config.getConfiguration().addDefault("game.short_players", "&aThere is not enough players. Countdown stopped.");
        config.getConfiguration().addDefault("game.end_short_players", "&aThe game has ended as too many players have left.");
        config.getConfiguration().addDefault("game.countdown.title", "&a%fallguys_countdown% seconds.");
        config.getConfiguration().addDefault("game.countdown.subtitle", "&auntil game starts.");
        config.getConfiguration().addDefault("game.available_arenas", "&aThese are the available arenas:");
        config.getConfiguration().addDefault("error.invalid_use", "&cInvalid use of command.");
        config.getConfiguration().addDefault("error.player_is_not_in_arena", "&cYou are not in an arena.");
        config.getConfiguration().addDefault("error.already_in_arena", "&cYou are already in an arena.");
        config.getConfiguration().addDefault("error.invalid_arena", "&cYou specified an invalid arena ID.");
        config.getConfiguration().addDefault("error.game-started", "&cGame already started.");
        config.getConfiguration().options().copyDefaults(true);
    }

    public void init() {
        saveDefaultLang();
    }
}
