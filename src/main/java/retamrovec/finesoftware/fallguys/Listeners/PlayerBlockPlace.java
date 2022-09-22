package retamrovec.finesoftware.fallguys.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import retamrovec.finesoftware.fallguys.FallGuys;

public class PlayerBlockPlace implements Listener {

    @EventHandler
    public void onBreak(BlockPlaceEvent e) {
        if (FallGuys.instance().getArenaManager().getArena(e.getPlayer()) == null) return;
        if (FallGuys.instance().getArenaManager().getArena(e.getPlayer()).getPlayers() == null) return;
        if (e.getPlayer().hasPermission("fallguys.blockplace")) return;
        e.setCancelled(true);
    }

}
