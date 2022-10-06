package retamrovec.finesoftware.fallguys.API;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;
import retamrovec.finesoftware.fallguys.Instance.*;

import java.util.List;
import java.util.UUID;

/**
 * @author RETAMROVEC
 * @version 1.0
 * @since 2022-10-6
 */
public class ArenaSettings implements ConfigHandler {

    private final Arena arena;

    public ArenaSettings(Arena arena) {
        this.arena = arena;
    }

    /*

    TOOLS

     */

    /**
     * Returns nothing, sends message to all players in the arena.
     * @param message Message that will be sent.
     */
    public void sendMessage(String message) {
        arena.sendMessage(message);
    }

    /**
     * Returns nothing, changes gamemode of all players in the arena.
     * @param gamemode Gamemode that will be used.
     */
    public void setGamemode(GameMode gamemode) {
        arena.setGamemode(gamemode);
    }

    /**
     * Returns nothing, sends title and subtitle to all players in the arena.
     * @param title Title that will be sent.
     * @param subtitle Subtitle that will be sent.
     */
    public void sendTitle(String title, String subtitle) {
        arena.sendTitle(title, subtitle);
    }

    /**
     * Returns nothing, teleports all players in the arena.
     * @param location Location where players will be teleported.
     */
    public void teleport(Location location) {
        arena.teleport(location);
    }

    /*

    PLAYERS

    */

    /**
     * Returns nothing, add player to arena.
     * @param player Player that will be used.
     */
    public void addPlayer(@NotNull Player player) {
        arena.addPlayer(player);
    }

    /**
     * Returns nothing, remove player from arena.
     * Used mostly when player leaves arena.
     * @param player Player that will be used.
     */
    public void removePlayer(@NotNull Player player) {
        arena.removePlayer(player);
    }

    /**
     * Returns nothing, remove player from arena.
     * This is different from removePlayer only by using it in kick events.
     * Like disqualifying because removePlayer uses similar system
     * But not same.
     * @param player Player that will be used.
     */
    public void kickPlayer(@NotNull Player player) {
        arena.kickPlayer(player);
    }
    /*

    INFO

    */

    /**
     * Returns id of the arena.
     * @return Primitive Integer
     */
    public int getId() {return arena.getId();}
    /**
     * Returns state of the arena.
     * @return GameState
     */
    public GameState getState() {return arena.getState();}
    /**
     * Returns all players in the arena.
     * @return List<UUID>
     */
    public List<UUID> getPlayers() {return arena.getPlayers();}

    /**
     * Returns nothing, update GameState to state.
     * @param state State which will be in update.
     */
    public void setState(GameState state) {arena.setState(state);}

    /**
     * Returns world where is arena.
     * @return World
     */
    public World getWorld() {return Bukkit.getWorld(getConfig().getString("arenas." + arena.getId() + ".world"));}
}
