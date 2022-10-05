package retamrovec.finesoftware.fallguys.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Events.FallGuysRoundEnd;

public class GameRoundEnd implements Listener {

    @EventHandler
    public void onRoundEnd(@NotNull FallGuysRoundEnd e) {
        e.getArena().getGame().reset();
    }
}
