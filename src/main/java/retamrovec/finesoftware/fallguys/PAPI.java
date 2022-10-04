package retamrovec.finesoftware.fallguys;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Instance.Arena;

public class PAPI extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "fallguys";
    }

    @Override
    public @NotNull String getAuthor() {
        return "RETAMROVEC";
    }

    @Override
    public @NotNull String getVersion() {
        return FallGuys.instance().getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        Arena arena = FallGuys.instance().getArenaManager().getArena(player);
        if (params.equalsIgnoreCase("arenaId")) {
            return String.valueOf(arena.getId());
        }
        if (params.equalsIgnoreCase("state")) {
            return arena.getState().toString();
        }
        if (params.equalsIgnoreCase("level")) {
            return String.valueOf(arena.getGame().levels.get(player.getUniqueId()));
        }
        if (params.equalsIgnoreCase("time")) {
            return String.valueOf(arena.getGame().getTime());
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
