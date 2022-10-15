package retamrovec.finesoftware.fallguys.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Builders.CommandBuilder;
import retamrovec.finesoftware.fallguys.Builders.CommandSenderBuilder;
import retamrovec.finesoftware.fallguys.Builders.PlayerBuilder;
import retamrovec.finesoftware.fallguys.Commands.SubCommands.*;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;
import retamrovec.finesoftware.fallguys.Handlers.FunctionsHandler;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RETAMROVEC
 * @since 11.10.2022
 * @version 1.0
 */
public class ArenaCommand implements CommandExecutor, ConfigHandler, LanguageHandler, FunctionsHandler {

    private final List<CommandSender> usedCommand = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        /*
        BOOLEANS
         */
        boolean help = args.length == 0 || args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help");
        boolean list = args[0].equalsIgnoreCase("list") && args.length == 1;
        boolean isPlayer = sender instanceof Player;
        boolean leave = args[0].equalsIgnoreCase("leave") && args.length == 1;
        boolean join = args[0].equalsIgnoreCase("join") && args.length == 2;
        boolean reload = args[0].equalsIgnoreCase("reload") && args.length == 2;
        /*
        SUBCOMMANDS
         */
        new CommandBuilder(help).option().True(() -> {
            HelpSubCommand helpSubCommand = new HelpSubCommand();
            helpSubCommand.onSubcommand(sender);
            usedCommand.add(sender);
        }).reset();
        new CommandBuilder(list).option().True(() -> {
            ListSubCommand listSubCommand = new ListSubCommand();
            listSubCommand.onSubcommand(sender);
            usedCommand.add(sender);
        }).reset();
        /*
        SUBCOMMANDS FOR ONLY PLAYERS
         */
        new CommandBuilder(isPlayer).option().False(() -> new CommandSenderBuilder(sender).sendMessage("&cYou can use this command only in-game currently.")).reset();
        Player player = (Player) sender;
        new CommandBuilder(leave).option().True(() -> {
            LeaveSubCommand leaveSubCommand = new LeaveSubCommand();
            leaveSubCommand.onSubcommand(player);
            usedCommand.add(sender);
        }).reset();
        new CommandBuilder(join).option().True(() -> {
            JoinSubCommand joinSubCommand = new JoinSubCommand();
            joinSubCommand.onSubcommand(player, args);
            usedCommand.add(sender);
        });
        new CommandBuilder(reload).option().True(() -> {
           ReloadSubCommand reloadSubCommand = new ReloadSubCommand();
           reloadSubCommand.onSubcommand(player, args);
           usedCommand.add(sender);
        });
        /*
        IF ARG DOESN'T EQUALS TO ANY SUBCOMMAND
         */
        if (!usedCommand.contains(sender)) {
            String commandNotFound = getLang().getString("error.invalid_use");
            new PlayerBuilder(player).sendPAPIMessage(commandNotFound);
        }
        return false;
    }
}
