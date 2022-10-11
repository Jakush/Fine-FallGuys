package retamrovec.finesoftware.fallguys.API;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import retamrovec.finesoftware.fallguys.Builders.CommandSenderBuilder;
import retamrovec.finesoftware.fallguys.Builders.PlayerBuilder;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Instance.Arena;

import java.util.List;

/**
 * @author RETAMROVEC
 * @version 1.0
 * @since 2022-10-9
 */
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
    public PlayerBuilder getPlayerBuilder(Player player) {
        return new PlayerBuilder(player);
    }
    public CommandSenderBuilder getCommandSenderBuilder(CommandSender sender) {
        return new CommandSenderBuilder(sender);
    }

}
