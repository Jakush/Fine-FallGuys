package retamrovec.finesoftware.fallguys.Commands.SubCommands;

import org.bukkit.entity.Player;
import retamrovec.finesoftware.fallguys.Builders.PlayerBuilder;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.Instance.Arena;

public class LeaveSubCommand implements LanguageHandler {

    public void onSubcommand(Player player) {
        Arena arena = FallGuys.instance().getArenaManager().getArena(player);
        if (arena != null) {
            new PlayerBuilder(player).sendPAPIMessage(getLang().getString("player.left_arena")).removePlayer(arena.getId(), player);
        }
        else {
            new PlayerBuilder(player).sendPAPIMessage(getLang().getString("error.player_is_not_in_arena"));
        }
    }

}
