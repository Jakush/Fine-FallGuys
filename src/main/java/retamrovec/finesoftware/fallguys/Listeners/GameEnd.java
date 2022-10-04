package retamrovec.finesoftware.fallguys.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import retamrovec.finesoftware.fallguys.Events.FallGuysGameEnd;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.PAPI;

public class GameEnd implements Listener, LanguageHandler {

    @EventHandler
    public void onGameEnd(FallGuysGameEnd e) {
        e.getArena().sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("game.end"), Bukkit.getPlayer(e.getFinisher()))));
        e.getArena().reset(true);
    }

}
