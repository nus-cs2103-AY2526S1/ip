package apleasebot.exceptions;

/**
 * Exception thrown when the formatting for the time provided by the user is wrong
 */
public class WrongTimeFormatException extends APleaseBotException {
    public WrongTimeFormatException(String message) {
        super(message);
    }
}
