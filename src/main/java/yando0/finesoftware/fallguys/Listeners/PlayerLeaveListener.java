package yando0.finesoftware.fallguys.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;
import yando0.finesoftware.fallguys.PAPI;

public class PlayerLeaveListener implements Listener {

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent e) {
        new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(ConfigManager.getConfiguration().getString("player.leave"), e.getPlayer())));
    }

}
