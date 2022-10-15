package retamrovec.finesoftware.fallguys.Listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import retamrovec.finesoftware.fallguys.Events.FallGuysGameEnd;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.PAPI;

import java.util.Random;
import java.util.UUID;

public class GameEnd implements Listener, LanguageHandler, ConfigHandler {

    @EventHandler
    public void onGameEnd(FallGuysGameEnd e) {
        e.getArena().sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("game.end"), Bukkit.getPlayer(e.getFinisher()))));
        e.getArena().reset(true);
        if (!getConfig().getBoolean("bungeecord")) return;
        Random random = new Random();
        int randomInt = random.nextInt(getConfig().getStringList("fallback-servers").size());
        String server = "unknown";
        for (int i = 0; i < getConfig().getStringList("fallback-servers").size(); i++) {
            if (i == randomInt) {
                server = getConfig().getStringList("fallback-servers").get(i);
            }
        }
        if (server.equals("unknown")) {
            Bukkit.getLogger().info("Unexpected error happened!");
            throw new NullPointerException("Fallback server was unknown.");
        }
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);

        for (UUID uuid : e.getPlayers()) {
            Player player = Bukkit.getPlayer(uuid);
            player.sendPluginMessage(FallGuys.instance(), "BungeeCord", out.toByteArray());
        }
    }

}
