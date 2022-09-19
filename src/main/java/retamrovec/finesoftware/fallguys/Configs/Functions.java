package retamrovec.finesoftware.fallguys.Configs;

import org.bukkit.plugin.java.JavaPlugin;
import retamrovec.finesoftware.fallguys.Handlers.FunctionsHandler;

public class Functions implements FunctionsHandler {

    /*

    This class is developed by RETAMROVEC.

     */

    private static JavaPlugin plugin;
    public Functions(JavaPlugin plugin) {
        Functions.plugin = plugin;
    }

    public static void newConfiguration() {
        plugin.saveResource("functions.yml", false);
    }

    public void init() {
        saveDefaultFunctions();
    }

}
