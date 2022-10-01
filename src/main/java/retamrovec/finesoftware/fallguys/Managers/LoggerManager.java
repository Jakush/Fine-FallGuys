package retamrovec.finesoftware.fallguys.Managers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import retamrovec.finesoftware.fallguys.Handlers.LoggerHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class LoggerManager implements LoggerHandler {

    private final Logger logger;
    @Setter
    private int logLevel = 0;

    public void logTrace(String message, Object... arguments) {
        if (logLevel < 2) return;
        logger.log(Level.INFO, "[TRACE] " + parseMessage(message, arguments));
    }

    public void logDebug(String message, Object... arguments) {
        if (logLevel < 1) return;
        logger.log(Level.INFO, "[DEBUG] " + parseMessage(message, arguments));
    }

    public void logInfo(String message, Object... arguments) {
        logger.log(Level.INFO, parseMessage(message, arguments));
    }

    public void logWarning(String message, Object... arguments) {
        logger.log(Level.WARNING, parseMessage(message, arguments));
    }

    public void logError(String message, Object... arguments) {
        logger.log(Level.SEVERE, parseMessage(message, arguments));
    }

    public void logError(Throwable error, String message, Object... arguments) {
        logger.log(Level.SEVERE, parseMessage(message, arguments), error);
    }

    private String parseMessage(@NonNull String message, @NonNull Object... arguments) {
        for (int i = 0; i < arguments.length; ++i)
            message = message.replace("%" + (i + 1), String.valueOf(arguments[i]));
        return message;
    }
}
