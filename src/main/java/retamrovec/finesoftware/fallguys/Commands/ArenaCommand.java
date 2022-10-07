package retamrovec.finesoftware.fallguys.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Builders.CommandSenderBuilder;
import retamrovec.finesoftware.fallguys.Builders.PlayerBuilder;
import retamrovec.finesoftware.fallguys.Commands.SubCommands.*;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;
import retamrovec.finesoftware.fallguys.Handlers.FunctionsHandler;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;

/**
 * @author RETAMROVEC
 */
public class ArenaCommand implements CommandExecutor, ConfigHandler, LanguageHandler, FunctionsHandler {

    public String colour(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 0 || args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help")) {
            HelpSubCommand helpSubCommand = new HelpSubCommand();
            helpSubCommand.onSubcommand(sender);
            return false;
        }
        if (args[0].equalsIgnoreCase("list") && args.length == 1) {
            ListSubCommand listSubCommand = new ListSubCommand();
            listSubCommand.onSubcommand(sender);
            return false;
        }
        if (!(sender instanceof Player)) {
            new CommandSenderBuilder(sender).sendMessage("&cYou can use this command only in-game currently.");
            return false;
        }
        Player player = (Player) sender;
        if (args[0].equalsIgnoreCase("leave") && args.length == 1) {
            LeaveSubCommand leaveSubCommand = new LeaveSubCommand();
            leaveSubCommand.onSubcommand(player);
            return false;
        }
        if (args[0].equalsIgnoreCase("join") && args.length == 2) {
            JoinSubCommand joinSubCommand = new JoinSubCommand();
            joinSubCommand.onSubcommand(player, args);
            return false;
        }
        if (args[0].equalsIgnoreCase("reload") && args.length == 2) {
            ReloadSubCommand reloadSubCommand = new ReloadSubCommand();
            reloadSubCommand.onSubcommand(player, args);
            return false;
        }
        new PlayerBuilder(player).sendPAPIMessage(getLang().getString("error.invalid_use"));
        return false;
    }
}
