package retamrovec.finesoftware.fallguys.Managers;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

public class BungeeManager implements PluginMessageListener {

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
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        String subchannel = in.readUTF();
        if (subchannel.equals("Bukkit-FallGuys")) {
            out.writeUTF("Connect");
            out.writeUTF("Connect");
        }
    }
}
