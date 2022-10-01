package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import retamrovec.finesoftware.fallguys.FallGuys;

public class TABCheck extends BukkitRunnable {

    public void start() {
        runTaskTimer(FallGuys.instance(), 0, 100);
    }

    @Override
    public void run() {
        if (FallGuys.instance().getOwnTAB()) {
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getNewScoreboard();
            Objective obj = board.registerNewObjective("dummy", "scoreboard");
        }
    }
}
