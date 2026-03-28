package jerry.exceptions;

/**
 * An exception that is thrown when user enters a command that is not recognized by the application.
 * This exception is used for invalid commands and display an appropriate error message that will help guide the user
 * in correcting their input command.
 */
public class InvalidCommandException extends JerryException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
