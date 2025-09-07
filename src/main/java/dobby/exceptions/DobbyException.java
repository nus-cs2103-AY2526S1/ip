package dobby.exceptions;

/**
 * Base class for all custom exceptions in Dobby.
 */
public class DobbyException extends Exception {
    public DobbyException(String message) {
        super(message);
    }
}
