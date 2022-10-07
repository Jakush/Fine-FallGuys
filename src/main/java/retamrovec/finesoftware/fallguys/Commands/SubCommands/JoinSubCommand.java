package retamrovec.finesoftware.fallguys.Commands.SubCommands;

import org.bukkit.entity.Player;
import retamrovec.finesoftware.fallguys.Builders.PlayerBuilder;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.Instance.Arena;

public class JoinSubCommand implements LanguageHandler {

    public void onSubcommand(Player player, String[] args) {
        int id;

        if (FallGuys.instance().getArenaManager().getArena(player) != null) {
            new PlayerBuilder(player).sendPAPIMessage(getLang().getString("error.already_in_arena"));
            return;
        }

        try {
            id = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            new PlayerBuilder(player).sendPAPIMessage(getLang().getString("error.already_in_arena"));
            return;
        }

        if (FallGuys.instance().getArenaManager().getArena(id) == null) {
            new PlayerBuilder(player).sendPAPIMessage(getLang().getString("error.already_in_arena"));
            return;
        }

        if (id >= 0 && id < FallGuys.instance().getArenaManager().getArenas().size()) {
            Arena arena = FallGuys.instance().getArenaManager().getArena(id);
            if (arena.getState() == (GameState.RECRUITING) || arena.getState() == GameState.COUNTDOWN) {
                new PlayerBuilder(player).sendPAPIMessage(getLang().getString("player.join_arena")).setArena(arena.getId());
            }
            else {
                new PlayerBuilder(player).sendPAPIMessage(getLang().getString("error.game-started"));
            }
        }
        else {
            new PlayerBuilder(player).sendPAPIMessage(getLang().getString("error.invalid_arena"));
        }
    }

}
