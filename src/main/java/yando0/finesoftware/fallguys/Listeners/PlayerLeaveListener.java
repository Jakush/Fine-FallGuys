package yando0.finesoftware.fallguys.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent e) {
        e.getPlayer().sendMessage(ChatColor.BLUE.BOLD + e.getPlayer().getName() + ChatColor.LIGHT_PURPLE + "Has Left the game!");

    }

}
