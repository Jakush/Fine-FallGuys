package retamrovec.finesoftware.fallguys.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.PAPI;

public class PlayerLeaveListener implements Listener, LanguageHandler {

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("player.leave"), e.getPlayer())));
    }

}
