package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;
import retamrovec.finesoftware.fallguys.PAPI;

import java.util.UUID;

public class Scoreboard extends BukkitRunnable implements ConfigHandler {

    private final UUID player;
    private int color = 1;
    public Scoreboard(UUID player) {
        this.player = player;
    }

    public void start() {
        runTaskTimer(FallGuys.instance(), 0, 20);
    }
    public void stop() {
        cancel();
    }

    @Override
    public void run() {
        if (!getConfig().getBoolean("scoreboard-enabled")) {
            cancel();
            return;
        }
        if (player == null) {
            FallGuys.instance().removeScoreboard();
            cancel();
            return;
        }
        if (FallGuys.instance().getOwnTAB()) {
            org.bukkit.scoreboard.Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective obj = board.registerNewObjective("dummy", "cat");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', PAPI.use(getConfig().getString("scoreboard-displayname"), Bukkit.getPlayer(player))));

            int size = getConfig().getStringList("scoreboard").size();
            for (String s : getConfig().getStringList("scoreboard")) {
                Score score = obj.getScore(ChatColor.translateAlternateColorCodes('&', String.valueOf(indetificator())) + ChatColor.translateAlternateColorCodes('&', PAPI.use(s, Bukkit.getPlayer(player))));
                score.setScore(size);
                size--;
            }
            Bukkit.getPlayer(player).setScoreboard(board);
            color = 0;
        }
    }

    public Object indetificator() {
        switch (color) {
            case 0:
            case 2:
            case 4:
            case 5:
            case 1:
            case 3:
            case 6:
            case 7:
            case 8:
            case 9:
                color++;
                return "&" + color + "&r";
            case 10:
                color++;
                return "&" + "f" + "&r";
            case 11:
                color++;
                return "&" + "a" + "&r";
            case 12:
                color++;
                return "&" + "e" + "&r";
            case 13:
                color++;
                return "&" + "c" + "&r";
            case 14:
                color++;
                return "&" + "d" + "&r";
            case 15:
                color++;
                return "&" + "b" + "&r";
            default:
                return "404! COLOR WAS NOT FOUND";
        }
    }
}
