package hermione.exceptions;

/**
 * Represents an exception that occurs during task validation in the Hermione application.
 */
public class TaskValidationException extends RuntimeException {
    public TaskValidationException(String message) {
        super("[ERROR] Task Error: " + message);
    }
}
