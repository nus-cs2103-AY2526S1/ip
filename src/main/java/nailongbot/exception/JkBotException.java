package nailongbot.exception;

/**
 * The base exception class for all exceptions thrown by NaiLongBot.
 * All custom exceptions in the bot should extend this class.
 * It allows for unified handling of bot-specific errors.
 */
public class JkBotException extends Exception {
    public JkBotException(String message) {
        super(message);
    }
}

