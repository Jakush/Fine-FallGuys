package yando0.finesoftware.fallguys;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
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

    public String onRequest(OfflinePlayer player, @NotNull String params) {
        for (Arena arena : arenaManager.getArenas()) {
            if (params.equalsIgnoreCase("countdown_" + arena.getId())) {
                if (arena.getState() != GameState.RECRUITING || arena.getState() != GameState.LIVE) {
                    Countdown countdown = new Countdown(arena);
                    return String.valueOf(countdown.countDownSeconds);
                }
            }
        }
        if (params.equalsIgnoreCase("arena_" + player.getName()) && player.isOnline()) {
            Arena arena = FallGuys.instance().getArenaManager().getArena(player, true);
            if (arena != null) {
                return String.valueOf(arena.getId());
            }
        }
        return null;
    }

    public static @NotNull String use(String message, Player player) {
        message = PlaceholderAPI.setPlaceholders(player, message);
        return message;
    }
}
