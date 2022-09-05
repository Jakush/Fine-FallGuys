package retamrovec.finesoftware.fallguys.Configs;

import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

public class Messages {

    public static void newConfiguration() {
        ConfigManager.getConfiguration().addDefault("player.join", "&9&l%player_name% &5has joined the game!");
        ConfigManager.getConfiguration().addDefault("player.leave", "&9&l%player_name% &5has left the game!");
        ConfigManager.getConfiguration().addDefault("game.countdown", "&aGame will start in %fallguys_countdown%&a seconds.");
        ConfigManager.getConfiguration().options().copyDefaults(true);
    }

}
