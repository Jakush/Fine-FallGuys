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
    private final Config config;
    public int i;
    public GameTime(Arena arena) {
        this.arena = arena;
        config = new Config(FallGuys.instance());
        this.i = config.getGameTime();
    }
    private final List<Integer> level = new ArrayList<>();

    public void start() {
        runTaskTimer(FallGuys.instance(), 0, 20);
    }

    @Override
    public void run() {
        if (i == 0) {
            level.add(level.size() + 1);
            for (UUID uuid : arena.getPlayers()) {
                Player p = Bukkit.getPlayer(uuid);
                if (p == null) {
                    cancel();
                    return;
                }
                int i2 = arena.getGame().levels.get(uuid);
                if (i2 < level.size()) {
                    FallGuys.instance().removeScoreboard();
                    FallGuys.instance().removeTablist();
                    arena.kickPlayer(p);
                    return;
                }
                p.setGameMode(GameMode.SURVIVAL);
                p.teleport(config.getArenaSpawn(arena.getId()));
            }
            i = config.getGameTime();
            return;
        }
        i--;
    }
}
