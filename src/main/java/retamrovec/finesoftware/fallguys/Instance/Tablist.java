package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;
import retamrovec.finesoftware.fallguys.PAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Tablist extends BukkitRunnable implements ConfigHandler {
    private final UUID player;
    public Tablist(UUID player) {
        this.player = player;
    }

    public void start() {
        runTaskTimer(FallGuys.instance(), 0, 20);
    }
    public void stop() {
        cancel();
    }

    @Override
    public void run() {
        if (!getConfig().getBoolean("tablist-enabled")) {
            cancel();
            return;
        }
        if (player == null) {
            FallGuys.instance().removeTablist();
            cancel();
            return;
        }
        if (FallGuys.instance().getOwnTAB()) {
            Player p = Bukkit.getPlayer(player);
            List<String> headerTab = new ArrayList<>(getConfig().getStringList("tablist-header"));
            List<String> footerTab = new ArrayList<>(getConfig().getStringList("tablist-footer"));
            StringBuilder header = new StringBuilder();
            StringBuilder footer = new StringBuilder();
            for (int i = 0; i < headerTab.size(); i++) {
                String str = headerTab.get(i);
                if (i == headerTab.size() - 1) {
                    header.append(ChatColor.translateAlternateColorCodes('&', PAPI.use(str, p)));
                }
                else {
                    header.append(ChatColor.translateAlternateColorCodes('&', PAPI.use(str, p))).append("\n");
                }            }
            for (int i = 0; i < footerTab.size(); i++) {
                String str = footerTab.get(i);
                if (i == footerTab.size() - 1) {
                    footer.append(ChatColor.translateAlternateColorCodes('&', PAPI.use(str, p)));
                }
                else {
                    footer.append(ChatColor.translateAlternateColorCodes('&', PAPI.use(str, p))).append("\n");
                }
            }
            p.setPlayerListHeader(header.toString());
            p.setPlayerListFooter(footer.toString());
        }
    }
}
