package yando0.finesoftware.fallguys;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Instance.Arena;
import retamrovec.finesoftware.fallguys.Instance.Countdown;
import retamrovec.finesoftware.fallguys.Managers.ArenaManager;

public class PAPI extends PlaceholderExpansion {

    FallGuys fallGuys;
    ArenaManager arenaManager;
    public PAPI(FallGuys fallGuys, ArenaManager arenaManager) {
        this.fallGuys = fallGuys;
        this.arenaManager = arenaManager;
    }


    @Override
    public @NotNull String getIdentifier() {
        return "fallguys";
    }

    @Override
    public @NotNull String getAuthor() {
        return "RETAMROVEC, Yando0, BoogeyMan";
    }

    @Override
    public @NotNull String getVersion() {
        return fallGuys.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        for (Arena arena : arenaManager.getArenas()) {
            if (params.equalsIgnoreCase("countdown_" + arena.getId())) {
                if (arena.getState() != GameState.RECRUITING || arena.getState() != GameState.LIVE) {
                    return String.valueOf(Countdown.countDownSeconds);
                }
            }
        }
        return null;
    }
}
