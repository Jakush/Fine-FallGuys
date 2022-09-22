package retamrovec.finesoftware.fallguys.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import retamrovec.finesoftware.fallguys.FallGuys;

public class PlayerBlockBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (FallGuys.instance().getArenaManager().getArena(e.getPlayer()) == null) return;
        if (FallGuys.instance().getArenaManager().getArena(e.getPlayer()).getPlayers() == null) return;
        if (e.getPlayer().hasPermission("fallguys.blockbreak")) return;
        e.setCancelled(true);
    }

}
