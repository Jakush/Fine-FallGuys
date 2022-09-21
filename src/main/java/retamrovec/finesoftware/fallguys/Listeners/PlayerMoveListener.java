package retamrovec.finesoftware.fallguys.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Handlers.FunctionsHandler;
import retamrovec.finesoftware.fallguys.Instance.Arena;
import retamrovec.finesoftware.fallguys.Managers.RegionManager;

public class PlayerMoveListener implements Listener, ConfigHandler, FunctionsHandler {

    Config config;
    public PlayerMoveListener(Config config) {
        this.config = config;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (FallGuys.instance().getArenaManager().getArena(e.getPlayer()) == null) return;
        if (FallGuys.instance().getArenaManager().getArena(e.getPlayer()).getPlayers() == null) return;
        if (FallGuys.instance().getArenaManager().getArena(e.getPlayer()).getPlayers().contains(e.getPlayer().getUniqueId())) {
            RegionManager regionManager = new RegionManager();
            Location location1 = new Location(
                    Bukkit.getWorld(getFunctions().getString("arenas.0.1.from.world")),
                    getFunctions().getDouble("arenas.0.1.from.x"),
                    getFunctions().getDouble("arenas.0.1.from.y"),
                    getFunctions().getDouble("arenas.0.1.from.z"),
                    getFunctions().getLong("arenas.0.1.from.yaw"),
                    getFunctions().getLong("arenas.0.1.from.pitch"));
            Location location2 = new Location(
                    Bukkit.getWorld(getFunctions().getString("arenas.0.1.to.world")),
                    getFunctions().getDouble("arenas.0.1.to.x"),
                    getFunctions().getDouble("arenas.0.1.to.y"),
                    getFunctions().getDouble("arenas.0.1.to.z"),
                    getFunctions().getLong("arenas.0.1.to.yaw"),
                    getFunctions().getLong("arenas.0.1.to.pitch"));
            if (regionManager.isInRegion(location1, location2, e.getPlayer().getLocation())) {
                if (e.getPlayer().getGameMode() == GameMode.SPECTATOR) return;
                Arena arena = FallGuys.instance().getArenaManager().getArena(e.getPlayer());
                arena.getGame().qualify(e.getPlayer(), true);
            }
        }
    }

}
