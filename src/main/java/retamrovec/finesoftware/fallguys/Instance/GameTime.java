package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.FallGuys;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameTime extends BukkitRunnable {

    private final Arena arena;
    public int i;
    public GameTime(Arena arena) {
        this.arena = arena;
    }
    private final List<Integer> level = new ArrayList<>();

    public void start() {
        runTaskTimer(FallGuys.instance(), 0, 20);
    }

    @Override
    public void run() {
        Config config = new Config(FallGuys.instance());
        if (i == config.getGameTime()) {
            level.add(level.size() + 1);
            for (UUID uuid : arena.getPlayers()) {
                Player p = Bukkit.getPlayer(uuid);
                if (p == null) {
                    cancel();
                    return;
                }
                int i2 = arena.getGame().levels.get(uuid);
                if (i2 == level.size()) {
                    arena.kickPlayer(p);
                }
                p.setGameMode(GameMode.SURVIVAL);
                p.teleport(config.getArenaSpawn(arena.getId()));
            }
            i = 0;
            return;
        }
        i++;
    }
}
