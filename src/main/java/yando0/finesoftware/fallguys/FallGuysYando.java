package yando0.finesoftware.fallguys;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

import java.io.File;


public class FallGuysYando {





    public void init() {
        // Here import all classes
        new ConfigManager(this.getDataFolder(), "config.yml");
        FallGuys.instance();

        Bukkit.getPluginManager().registerEvents((Listener) this, (Plugin) this);

    }

    private File getDataFolder() {
        return null;
    }

}
