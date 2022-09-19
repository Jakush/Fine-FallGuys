package retamrovec.finesoftware.fallguys.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Instance.Game;
import retamrovec.finesoftware.fallguys.Managers.ArenaManager;
import retamrovec.finesoftware.fallguys.Managers.RegionManager;

public class PlayerMoveListener implements Listener, ConfigHandler {

    Config config;
    public PlayerMoveListener(Config config) {
        this.config = config;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        ArenaManager arenaManager = new ArenaManager(config);
        if (arenaManager.getArena(e.getPlayer()) == null) {
            return;
        }
        RegionManager regionManager = new RegionManager();
        Location location1 = regionManager.transformLocation(
                getConfig().getString("arenas.0.1.from.world"),
                getConfig().getDouble("arenas.0.1.from.x"),
                getConfig().getDouble("arenas.0.1.from.y"),
                getConfig().getDouble("arenas.0.1.from.z"),
                getConfig().getLong("arenas.0.1.from.yaw"),
                getConfig().getLong("arenas.0.1.from.pitch"));
        Location location2 = regionManager.transformLocation(
                getConfig().getString("arenas.0.1.to.world"),
                getConfig().getDouble("arenas.0.1.to.x"),
                getConfig().getDouble("arenas.0.1.to.y"),
                getConfig().getDouble("arenas.0.1.to.z"),
                getConfig().getLong("arenas.0.1.to.yaw"),
                getConfig().getLong("arenas.0.1.to.pitch"));
        World world1 = Bukkit.getWorld(getConfig().getString("arenas.0.1.from.world"));
        World world2 = e.getPlayer().getWorld();
        if (regionManager.isInRegion(location1, location2, e.getPlayer().getLocation(), world1, world2)) {
            Game game = new Game(arenaManager.getArena(e.getPlayer()));
            game.qualify(e.getPlayer());
        }
    }

}
