package retamrovec.finesoftware.fallguys.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Instance.Arena;
import retamrovec.finesoftware.fallguys.Instance.Game;

import java.util.List;
import java.util.UUID;

public class FallGuysRoundEnd extends Event {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private final List<UUID> players;
    private final Arena arena;
    public FallGuysRoundEnd(List<UUID> players, Arena arena){
        this.players = players;
        this.arena = arena;
    }

    public List<UUID> getPlayers() {
        return players;
    }
    public Arena getArena() { return arena;}
    public Game getGame() { return arena.getGame(); }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
