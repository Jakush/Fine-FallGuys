package retamrovec.finesoftware.fallguys.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.PAPI;

public class  PlayerJoinListener implements Listener, LanguageHandler {

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("player.join"), e.getPlayer())));
    }

}
