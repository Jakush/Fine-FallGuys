package yando0.finesoftware.fallguys;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

import java.io.File;


public class FallGuysYando {





    public void init() {
        // Here import all classes
        new ConfigManager("config.yml", this.getDataFolder());
        FallGuys.instance();
    }

    private File getDataFolder() {
        return null;
    }

}
