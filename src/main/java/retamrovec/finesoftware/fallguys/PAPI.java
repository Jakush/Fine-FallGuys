package retamrovec.finesoftware.fallguys;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Instance.Arena;
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
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (params.equalsIgnoreCase("arena_" + player.getName())) {
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

    public static String use(String message, boolean useRandomPlayer) {
        if (useRandomPlayer) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                message = PlaceholderAPI.setPlaceholders(p, message);
                return message;
            }
        }
        return null;
    }
}
