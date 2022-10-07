package retamrovec.finesoftware.fallguys.Builders;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import retamrovec.finesoftware.fallguys.PAPI;


/**
 * @author RETAMROVEC
 * @version 1.0
 * @since 2022-10-7
 */
public class CommandSenderBuilder {

    private final CommandSender sender;
    /**
     * Class for changing parameters for player.
     * @param sender CommandSender that will be used.
     */
    public CommandSenderBuilder(CommandSender sender) {
        this.sender = sender;
    }

    /**
     * Sends raw message. Will do not translate messages to colors.
     * @param message Message used.
     * @return Returns this class.
     */
    public CommandSenderBuilder sendRawMessage(String message) {
        this.sender.sendMessage(message);
        return this;
    }

    /**
     * Sends message. Message will be translated to colors.
     * Will do not support placeholders from PAPI.
     * @param message Message used.
     * @return Returns this class.
     */
    public CommandSenderBuilder sendMessage(String message) {
        this.sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        return this;
    }
}
