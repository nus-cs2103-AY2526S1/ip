package apleasebot.exceptions;

/**
 * Parent class for most exceptions thrown from the program
 */
public class APleaseBotException extends RuntimeException {
    public APleaseBotException(String message) {
        super("APleaseBot error: " + message);
    }
}
