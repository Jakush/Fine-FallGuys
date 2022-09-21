package retamrovec.finesoftware.fallguys.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import retamrovec.finesoftware.fallguys.FallGuys;

import java.util.Random;

public class PlayerMoveOnSlime implements Listener {

    @EventHandler
    public void onSlimeMove(PlayerMoveEvent e) {
        if (FallGuys.instance().getArenaManager().getArena(e.getPlayer()) == null) return;
        if (FallGuys.instance().getArenaManager().getArena(e.getPlayer()).getPlayers() == null) return;
        Player player = e.getPlayer();
        Block under = player.getLocation().clone().subtract(0.0D, 1.0D, 0.0D).getBlock();
        if (under.getType() == Material.SLIME_BLOCK) {
            PotionEffect effect = new PotionEffect(PotionEffectType.SLOW, 10, 1, false, false);
            player.addPotionEffect(effect);
        }
    }
}
