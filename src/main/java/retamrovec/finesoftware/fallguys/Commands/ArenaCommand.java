package retamrovec.finesoftware.fallguys.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Instance.Arena;
import retamrovec.finesoftware.fallguys.Instance.Game;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;
import yando0.finesoftware.fallguys.PAPI;

public class ArenaCommand implements CommandExecutor, ConfigHandler, LanguageHandler {

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

        if (args[0].equalsIgnoreCase("list") && args.length == 1) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("game.available_arenas"), null)));
            for (Arena arena : FallGuys.instance().getArenaManager().getArenas()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use("&7- " + arena.getId() + "&8(&6" + arena.getState() + "&8)", player)));
            }
            return false;
        }

        if (args[0].equalsIgnoreCase("leave") && args.length == 1) {
            Arena arena = FallGuys.instance().getArenaManager().getArena(player);
            if (arena != null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("player.left_arena"), player)));
                arena.removePlayer(player);
            }
            else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("error.player_is_not_in_arena"), player)));
            }
            return false;
        }

        if (args[0].equalsIgnoreCase("join") && args.length == 2) {
            int id;

            if (FallGuys.instance().getArenaManager().getArena(player) != null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', getLang().getString("error.already_in_arena")));
                return false;
            }

            try {
                id = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("error.invalid_arena"), null)));
                return false;
            }

            if (FallGuys.instance().getArenaManager().getArena(id) == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', getLang().getString("error.already_in_arena")));
                return false;
            }

            if (id >= 0 && id < FallGuys.instance().getArenaManager().getArenas().size()) {
                Arena arena = FallGuys.instance().getArenaManager().getArena(id);
                if (arena.getState() == (GameState.RECRUITING) || arena.getState() == GameState.COUNTDOWN) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("player.join_arena"), player)));
                    arena.addPlayer(player);
                    return false;
                }
                else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("error.game-started"), null)));
                }
            }
            else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("error.invalid_arena"), null)));
            }
            return false;
        }
        if (args[0].equalsIgnoreCase("reload") && args.length == 2) {
            if (args[1].equalsIgnoreCase("config")) {
                saveConfig();
                reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("success.reload.config"), player)));
                return true;
            }
            if (args[1].equalsIgnoreCase("lang") || args[1].equalsIgnoreCase("language")) {
                saveLang();
                reloadLang();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("success.reload.lang"), player)));
                return true;
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("error.invalid_use"), player)));
            return false;
        }
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("error.invalid_use"), player)));
        return false;
    }
}
