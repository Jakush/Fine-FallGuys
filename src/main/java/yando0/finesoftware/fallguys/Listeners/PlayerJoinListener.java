package yando0.finesoftware.fallguys.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {
        e.getPlayer().sendMessage(ChatColor.BLUE.BOLD + e.getPlayer().getName() + ChatColor.LIGHT_PURPLE + "Has joined the game!");

    }

}
