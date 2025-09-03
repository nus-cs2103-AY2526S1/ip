package evansbot.Exceptions;

/**
 * Represents a generic exception that can occur in the EvansBot application.
 * All custom exceptions in the application should extend this class.
 */
public class EvansBotException extends Exception {
    /**
     * Constructs an EvansBotException with the specified error message.
     *
     * @param message Description of the exception.
     */
    public EvansBotException(String message) {
        super(message);
    }
}
