package yando0.finesoftware.fallguys;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

import java.io.File;


public class FallGuysYando {


    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {
        e.getPlayer().sendMessage(ChatColor.BLUE.BOLD + e.getPlayer().getName() + ChatColor.LIGHT_PURPLE + "Has joined the game!");

    }
    @EventHandler
    public void onquitevnet(PlayerQuitEvent e) {
        e.getPlayer().sendMessage(ChatColor.BLUE.BOLD + e.getPlayer().getName() + ChatColor.LIGHT_PURPLE + "Has Left the game!");

    }


    public void init() {
        // Here import all classes
        new ConfigManager("config.yml", this.getDataFolder());
        ConfigManager.newConfiguration();
        ConfigManager.saveConfiguration();
        FallGuys.instance();
    }

    private File getDataFolder() {
        return null;
    }

}
