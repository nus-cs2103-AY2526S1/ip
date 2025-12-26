package friday.exceptions;

/**
 * Represents an unchecked exception where the user is inputting an unknown command
 * into the system.
 */
public class UnknownCommandFridayException extends Exception {
    public UnknownCommandFridayException(String message) {
        super(message);
    }
}
