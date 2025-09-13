package hermione.exceptions;

/**
 * Represents an exception that occurs when an invalid command is encountered in the Hermione application.
 */
public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(String message) {
        super("[ERROR] Invalid command: " + message);
    }
}
