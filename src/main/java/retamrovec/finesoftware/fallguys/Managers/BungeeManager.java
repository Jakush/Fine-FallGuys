package retamrovec.finesoftware.fallguys.Managers;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;

public class BungeeManager implements PluginMessageListener, ConfigHandler {

    private final JavaPlugin plugin;
    public BungeeManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerBungeecord() {
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", this);
    }

    public void unregisterBungeecord() {
        plugin.getServer().getMessenger().unregisterOutgoingPluginChannel(plugin, "BungeeCord");
        plugin.getServer().getMessenger().unregisterIncomingPluginChannel(plugin, "BungeeCord", this);
    }

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) return;
    }
}
