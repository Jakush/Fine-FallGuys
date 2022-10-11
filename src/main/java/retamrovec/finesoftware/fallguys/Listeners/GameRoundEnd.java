package retamrovec.finesoftware.fallguys.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Events.FallGuysRoundEnd;
import retamrovec.finesoftware.fallguys.FallGuys;

import java.util.UUID;

/**
 * @author RETAMROVEC
 * @version 1.0
 * @since 2022-10-9
 */
public class GameRoundEnd implements Listener {

    private final Config config = new Config(FallGuys.instance());

    @EventHandler
    public void onRoundEnd(@NotNull FallGuysRoundEnd e) {
        e.getGame().reset();
        if (e.getGame().getLevel().size() == 2) {
            Location location = config.getMapSpawn(e.getArena().getId(), e.getGame().getLevel().size());
            for (UUID uuid : e.getPlayers()) {
                Player p = Bukkit.getPlayer(uuid);
                assert p != null;
                p.teleport(location);
            }
        }
    }
}
