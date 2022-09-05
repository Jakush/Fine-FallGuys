package retamrovec.finesoftware.fallguys.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Instance.Arena;
import retamrovec.finesoftware.fallguys.Managers.ArenaManager;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;
import yando0.finesoftware.fallguys.PAPI;

public class ArenaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {

        }
        Player player = (Player) sender;

        if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("game.available_arenas"), null)));
            for (Arena arena : FallGuys.instance().getArenaManager().getArenas()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use("&7- " + arena.getId() + "&8(&6" + arena.getState() + "&8)", player)));
            }
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {
            Arena arena = FallGuys.instance().getArenaManager().getArena(player);
            if (arena != null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("player.left_arena"), player)));
                arena.removePlayer(player);
            }
            else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("error.player_is_not_in_arena"), player)));
            }
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("join")) {
            if (FallGuys.instance().getArenaManager().getArena(player) != null) {
                //ConfigManager.getConfiguration().getString(ChatColor.translateAlternateColorCodes('&', ))
                return false;
            }
            Arena arena = FallGuys.instance().getArenaManager().getArena(player);
            if (arena != null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("player.left_arena"), player)));
                arena.removePlayer(player);
            }
            else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("error.player_is_not_in_arena"), player)));
            }
        }
        else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("error.invalid_use"), player)));
        }


        return false;
    }
}
