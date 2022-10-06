package retamrovec.finesoftware.fallguys.API;

import org.bukkit.entity.Player;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Instance.Arena;

import java.util.List;

public class FallGuysAPI {



    public Arena getArena(Player player) {
        return FallGuys.instance().getArenaManager().getArena(player);
    }

    public Arena getArena(int id) {
        return FallGuys.instance().getArenaManager().getArena(id);
    }

    public List<Arena> getArenas() {
        return FallGuys.instance().getArenaManager().getArenas();
    }

    public ArenaSettings getArenaSettings(Arena arena) {
        return new ArenaSettings(arena);
    }

}
