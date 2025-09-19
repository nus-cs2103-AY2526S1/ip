package dobby.exceptions;

/** Thrown when a user enters an invalid command. */

public class InvalidCommandException extends DobbyException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
