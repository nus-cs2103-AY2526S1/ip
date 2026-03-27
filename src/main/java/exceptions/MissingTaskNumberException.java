package exceptions;

/**
 * Thrown when a command that requires a task number is issued without one.
 */
public class MissingTaskNumberException extends SundayException {
    public MissingTaskNumberException() {
        super("Usage: \"mark\" followed by a number.");
    }
}
