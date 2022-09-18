package retamrovec.finesoftware.fallguys.Configs;

import org.bukkit.plugin.java.JavaPlugin;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;

public class Messages implements LanguageHandler {

    /*

    This class is developed by RETAMROVEC.

     */

    private static JavaPlugin plugin;
    public Messages(JavaPlugin plugin) {
        Messages.plugin = plugin;
    }

    public static void newConfiguration() {
        plugin.saveResource("messages.yml", false);
    }

    public void init() {
        saveDefaultLang();
    }
}
