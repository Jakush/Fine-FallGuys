package retamrovec.finesoftware.fallguys.Listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import retamrovec.finesoftware.fallguys.FallGuys;

public class PlayerMoveOnIce implements Listener {

    @EventHandler
    public void onIceMove(PlayerMoveEvent e) {
        if (FallGuys.instance().getArenaManager().getArena(e.getPlayer()) == null) return;
        if (FallGuys.instance().getArenaManager().getArena(e.getPlayer()).getPlayers() == null) return;
        Player player = e.getPlayer();
        Block under = player.getLocation().clone().subtract(0.0D, 1.0D, 0.0D).getBlock();
        if (under.getType() == Material.PACKED_ICE) {
            PotionEffect effect = new PotionEffect(PotionEffectType.SPEED, 10, 1, false, false);
            PotionEffect effect2 = new PotionEffect(PotionEffectType.CONFUSION, 200, 255, false, false);
            player.addPotionEffect(effect);
            player.addPotionEffect(effect2);
            return;
        }
        if (under.getType() == Material.ICE) {
            PotionEffect effect = new PotionEffect(PotionEffectType.SPEED, 10, 1, false, false);
            player.addPotionEffect(effect);
            return;
        }
        if (player.hasPotionEffect(PotionEffectType.CONFUSION)) {
            player.removePotionEffect(PotionEffectType.CONFUSION);
        }
    }
}
