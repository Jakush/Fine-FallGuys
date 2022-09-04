package yando0.finesoftware.fallguys;

import org.bukkit.Bukkit;
import retamrovec.finesoftware.fallguys.FallGuys;
import yando0.finesoftware.fallguys.Listeners.PlayerJoinListener;
import yando0.finesoftware.fallguys.Listeners.PlayerLeaveListener;

public class FallGuysYando {


    public void init() {
        // Here import all classes
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), FallGuys.instance());
        Bukkit.getPluginManager().registerEvents(new PlayerLeaveListener(), FallGuys.instance());
    }

}
