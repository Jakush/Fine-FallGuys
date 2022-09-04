package retamrovec.finesoftware.fallguys.Managers;

public class Messages {

    public static void newConfiguration() {
        ConfigManager.getConfiguration().addDefault("&9&l%player_name% &5has joined the game!", 2);
        ConfigManager.getConfiguration().addDefault("&9&l%player_name% &5has left the game!", 2);
        ConfigManager.getConfiguration().options().copyDefaults(true);
    }

}
