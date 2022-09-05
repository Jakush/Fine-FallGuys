package retamrovec.finesoftware.fallguys.Configs;

import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

public class Messages {

    public static void newConfiguration() {
        ConfigManager.getConfiguration().addDefault("player.join", "&9&l%player_name% &5has joined the game!");
        ConfigManager.getConfiguration().addDefault("player.leave", "&9&l%player_name% &5has left the game!");
        ConfigManager.getConfiguration().addDefault("player.qualified", "&aYou has been qualified in this round. &9Congratulations!");
        ConfigManager.getConfiguration().addDefault("game.countdown", "&aGame will start in %fallguys_countdown%&a seconds.");
        ConfigManager.getConfiguration().addDefault("game.start", "&aGame has started. Get to the finish line first to get qualified. Good luck!");
        ConfigManager.getConfiguration().addDefault("game.end", "&9&l%player_name% &ahas won! Thanks for playing, hope you will come on this mini-game soon as possible!");
        ConfigManager.getConfiguration().options().copyDefaults(true);
    }

}
