package retamrovec.finesoftware.fallguys.Commands.SubCommands;

import org.bukkit.command.CommandSender;
import retamrovec.finesoftware.fallguys.Builders.CommandSenderBuilder;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.Instance.Arena;

public class ListSubCommand implements LanguageHandler {

    public void onSubcommand(CommandSender sender) {
        new CommandSenderBuilder(sender).sendMessage(getLang().getString("game.available_arenas"));
        for (Arena arena : FallGuys.instance().getArenaManager().getArenas()) {
            new CommandSenderBuilder(sender).sendMessage("&7- " + arena.getId() + "&8(&6" + arena.getState() + "&8)");
        }
    }
}
