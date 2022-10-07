package retamrovec.finesoftware.fallguys.Commands.SubCommands;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import retamrovec.finesoftware.fallguys.Builders.CommandSenderBuilder;

public class HelpSubCommand {

    public void onSubcommand(CommandSender sender) {
        new CommandSenderBuilder(sender).sendMessage("&c------ &9FALLGUYS &c------");
        new CommandSenderBuilder(sender).sendMessage("&c> &b/fallguys reload config");
        new CommandSenderBuilder(sender).sendMessage("&c> &b/fallguys reload language|lang");
        new CommandSenderBuilder(sender).sendMessage("&c> &b/fallguys reload functions|func");
        new CommandSenderBuilder(sender).sendMessage("&c> &b/fallguys list");
        new CommandSenderBuilder(sender).sendMessage("&c> &b/fallguys leave");
        new CommandSenderBuilder(sender).sendMessage("&c> &b/fallguys join <ID>");
        if (!(sender instanceof Player)) {return;}
        Player player = (Player) sender;
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
        player.spawnParticle(Particle.HEART, player.getLocation(), 10);
    }
}
