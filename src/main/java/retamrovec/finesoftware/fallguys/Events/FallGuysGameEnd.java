package retamrovec.finesoftware.fallguys.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Instance.Arena;

import java.util.List;
import java.util.UUID;

/**
 * @author RETAMROVEC
 * @version 1.0
 * @since 2022-10-9
 */
public class FallGuysGameEnd extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private final List<UUID> players;
    private final Arena arena;
    private final UUID finisher;
    public FallGuysGameEnd(List<UUID> players, UUID finisher, Arena arena){
        this.players = players;
        this.arena = arena;
        this.finisher = finisher;
    }

    public List<UUID> getPlayers() {
        return players;
    }
    public Arena getArena() { return arena;}
    public UUID getFinisher() { return finisher; }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
