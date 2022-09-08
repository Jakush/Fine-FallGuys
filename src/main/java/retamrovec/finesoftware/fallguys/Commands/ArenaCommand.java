package retamrovec.finesoftware.fallguys.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Instance.Arena;
import retamrovec.finesoftware.fallguys.Instance.Game;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;
import yando0.finesoftware.fallguys.PAPI;

public class ArenaCommand implements CommandExecutor {

    /*

    This class is developed by RETAMROVEC.

     */

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou can use this command only in-game currently."));
            return false;
        }
        Player player = (Player) sender;

        if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
            new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("game.available_arenas"), null)));
            for (Arena arena : FallGuys.instance().getArenaManager().getArenas()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use("&7- " + arena.getId() + "&8(&6" + arena.getState() + "&8)", player)));
            }
            return false;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {
            new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
            Arena arena = FallGuys.instance().getArenaManager().getArena(player);
            if (arena != null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("player.left_arena"), player)));
                arena.removePlayer(player);
            }
            else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("error.player_is_not_in_arena"), player)));
            }
            return false;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
            new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
            if (FallGuys.instance().getArenaManager().getArena(player) != null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfiguration().getString("error.already_in_arena")));
                return false;
            }
            int id;
            try {
                id = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("error.invalid_arena"), null)));
                return false;
            }

            if (id >= 0 && id < FallGuys.instance().getArenaManager().getArenas().size()) {
                Arena arena = FallGuys.instance().getArenaManager().getArena(id);
                if (arena.getState() == GameState.RECRUITING || arena.getState() == GameState.COUNTDOWN) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("player.join_arena"), player)));
                    arena.addPlayer(player);
                }
                else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("error.game-started"), null)));
                }
            }
            else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("error.invalid_arena"), null)));
            }
            return false;
        }
        else {
            new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("error.invalid_use"), player)));
        }


        return false;
    }
}
