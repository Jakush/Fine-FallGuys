package retamrovec.finesoftware.fallguys.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Instance.Arena;

import java.util.List;
import java.util.UUID;

public class FallGuysGameEnd extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;
    private final List<UUID> players;
    private final Arena arena;
    private final UUID finisher;
    public FallGuysGameEnd(List<UUID> players, UUID finisher, Arena arena){
        this.isCancelled = false;
        this.players = players;
        this.arena = arena;
        this.finisher = finisher;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
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
