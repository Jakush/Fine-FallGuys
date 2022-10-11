package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.PAPI;

/**
 * @author RETAMROVEC
 * @version 1.0
 * @since 2022
 */
public class Countdown extends BukkitRunnable implements LanguageHandler {

    private final Arena arena;
    public int countDownSeconds;

    public Countdown(Arena arena) {
        this.arena = arena;
        Config config = new Config(FallGuys.instance());
        countDownSeconds = config.getCountdownSeconds();
    }

    public void start() {
        arena.setState(GameState.COUNTDOWN);
        runTaskTimer(FallGuys.instance(), 0, 20);
    }

    @Override
    public void run() {
        if (countDownSeconds == 0) {
            arena.start();
            cancel();
            return;
        }

        if (countDownSeconds <= 10 || countDownSeconds % 15 == 0) {
            String message = PAPI.use(getLang().getString("game.countdown.message"), true);
            String format = message
                    .replace("%fallguys_countdown%", String.valueOf(countDownSeconds));
            arena.sendMessage(ChatColor.translateAlternateColorCodes('&', format));
        }

        String message = PAPI.use(getLang().getString("game.countdown.title"), true);
        String format = message
                .replace("%fallguys_countdown%", String.valueOf(countDownSeconds));

        String title = ChatColor.translateAlternateColorCodes('&', format);

        String message1 = PAPI.use(getLang().getString("game.countdown.subtitle"), true);
        String format1 = message1
                .replace("%fallguys_countdown%", String.valueOf(countDownSeconds));

        String subTitle = ChatColor.translateAlternateColorCodes('&', format1);
        arena.sendTitle(title, subTitle);

        countDownSeconds--;
    }
}
