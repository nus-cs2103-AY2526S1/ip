package steven.exception;

/**
 * Thrown when the user does not follow the correct syntax for marking and unmarking tasks as done.
 */
public class InvalidMarkFormatException extends StevenException {
    public InvalidMarkFormatException() {
        super("\tPls use the correct format for marking/unmarking tasks");
    }
}
