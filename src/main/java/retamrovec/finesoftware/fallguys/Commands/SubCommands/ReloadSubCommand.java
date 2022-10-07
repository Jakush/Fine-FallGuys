package retamrovec.finesoftware.fallguys.Commands.SubCommands;

import org.bukkit.entity.Player;
import retamrovec.finesoftware.fallguys.Builders.PlayerBuilder;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;
import retamrovec.finesoftware.fallguys.Handlers.FunctionsHandler;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;

public class ReloadSubCommand implements LanguageHandler, ConfigHandler, FunctionsHandler {

    public void onSubcommand(Player player, String[] args) {
        if (args[1].equalsIgnoreCase("config")) {
            saveConfig();
            reloadConfig();
            new PlayerBuilder(player).sendPAPIMessage(getLang().getString("success.reload.config"));
            return;
        }
        if (args[1].equalsIgnoreCase("lang") || args[1].equalsIgnoreCase("language")) {
            saveLang();
            reloadLang();
            new PlayerBuilder(player).sendPAPIMessage(getLang().getString("success.reload.lang"));
            return;
        }
        if (args[1].equalsIgnoreCase("func") || args[1].equalsIgnoreCase("functions")) {
            saveFunctions();
            reloadFunctions();
            new PlayerBuilder(player).sendPAPIMessage(getLang().getString("success.reload.func"));
            return;
        }
        new PlayerBuilder(player).sendPAPIMessage(getLang().getString("error.invalid_use"));
    }
}
