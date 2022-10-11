package retamrovec.finesoftware.fallguys.Maps.Map2;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;
import retamrovec.finesoftware.fallguys.Instance.Arena;

public class Spinners implements Listener, ConfigHandler {

    @EventHandler
    public void onMoveOnSpinner(PlayerMoveEvent e) {
        if (FallGuys.instance().getArenaManager().getArena(e.getPlayer()) == null) return;
        if (FallGuys.instance().getArenaManager().getArena(e.getPlayer()).getPlayers() == null) return;
        if (FallGuys.instance().getArenaManager().getArena(e.getPlayer()).getState() != GameState.LIVE) return;
        Arena arena = FallGuys.instance().getArenaManager().getArena(e.getPlayer());
        int id = arena.getId();
        String material = getConfig().getString("arenas." + id + ".spinner-block");
        if (material == null) {
            throw new NullPointerException("Material cannot be found!");
        }
        if (material.isEmpty()) return;
        Player player = e.getPlayer();
        Block under = player.getLocation().clone().subtract(0.0D, 1.0D, 0.0D).getBlock();
        if (under.getType() == Material.matchMaterial(material)) {
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, 1, 3,false, false);
            player.addPotionEffect(potionEffect);
        }
    }
}
