package retamrovec.finesoftware.fallguys.Builders;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.PAPI;

import java.util.UUID;


/**
 * @author RETAMROVEC
 * @version 1.0
 * @since 2022-10-7
 */
public class PlayerBuilder {

    private final Player player;
    /**
     * Class for changing parameters for player.
     * @param player Player that will be used.
     */
    public PlayerBuilder(Player player) {
        this.player = player;
    }

    public PlayerBuilder(UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);
    }

    /**
     * Sets players arena.
     * WILL DO NOT SEND MESSAGE.
     * @param ID ID of arena.
     * @return Returns this class.
     */
    public PlayerBuilder setArena(int ID) {
        FallGuys.instance().getArenaManager().getArena(ID).addPlayer(this.player);
        return this;
    }

    public PlayerBuilder addPlayer(int ID, @NotNull Player player) {
        FallGuys.instance().getArenaManager().getArena(ID).addPlayer(player);
        return this;
    }

    /**
     * Returns nothing, remove player from arena.
     * Used mostly when player leaves arena.
     * @param ID ID of arena.
     * @param player Player that will be used.
     */
    public PlayerBuilder removePlayer(int ID, @NotNull Player player) {
        FallGuys.instance().getArenaManager().getArena(ID).removePlayer(player);
        return this;
    }

    /**
     * Returns nothing, remove player from arena.
     * This is different from removePlayer only by using it in kick events.
     * Like disqualifying because removePlayer uses similar system
     * But not same.
     * @param ID ID of arena.
     * @param player Player that will be used.
     */
    public PlayerBuilder kickPlayer(int ID, @NotNull Player player) {
        FallGuys.instance().getArenaManager().getArena(ID).kickPlayer(player);
        return this;
    }

    /**
     * Teleports player.
     * @param ID ID of arena.
     * @param location Location where will be player teleported.
     * @return Returns this class.
     */
    public PlayerBuilder teleport(int ID, Location location) {
        this.player.teleport(location);
        return this;
    }

    /**
     * Sets gamemode to a player.
     * @param gamemode Gamemode for player.
     * @return Returns this class.
     */
    public PlayerBuilder setGamemode(GameMode gamemode) {
        this.player.setGameMode(gamemode);
        return this;
    }

    /**
     * Sends raw message. Will do not translate messages to colors.
     * @param message Message used.
     * @return Returns this class.
     */
    public PlayerBuilder sendRawMessage(String message) {
        this.player.sendMessage(message);
        return this;
    }

    /**
     * Sends message. Message will be translated to colors.
     * Will do not support placeholders from PAPI.
     * @param message Message used.
     * @return Returns this class.
     */
    public PlayerBuilder sendMessage(String message) {
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        return this;
    }

    /**
     * Sends message. Message will be translated to colors,
     * and will support placeholders from PAPI.
     * @param message Message used.
     * @return Returns this class.
     */
    public PlayerBuilder sendPAPIMessage(String message) {
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(message, this.player)));
        return this;
    }
}
