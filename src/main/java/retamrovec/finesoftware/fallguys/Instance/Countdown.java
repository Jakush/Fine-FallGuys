package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Managers.ConfigManager;

public class Countdown extends BukkitRunnable {

    private Arena arena;
    public static int countDownSeconds;

    public Countdown(Arena arena) {
        this.arena = arena;
        countDownSeconds = Config.getCountdownSeconds();
    }

    public void start() {
        arena.setState(GameState.COUNTDOWN);
        runTaskTimer(FallGuys.instance(), 0, 20);
    }

    @Override
    public void run() {
        new ConfigManager(FallGuys.instance().getDataFolder(), "messages.yml");
        if (countDownSeconds == 0) {
            cancel();
            // arena start
            return;
        }

        if (countDownSeconds <= 10 || countDownSeconds % 15 == 0) {
            arena.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfiguration().getString("game.countdown")));
        }

        countDownSeconds--;
    }
}
