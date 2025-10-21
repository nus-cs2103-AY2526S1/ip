package geegar.exception;

/**
 * Detects if the exception thrown is specific to the Geegar Chatbot
 * Serves as the base class for all custom exceptions thrown by the bot
 */
public class GeegarException extends Exception {

    public GeegarException(String message) {
        super(message);
    }
}