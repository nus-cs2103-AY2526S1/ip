package exceptions;

/**
 * Thrown when a deadline command is missing its description.
 */
public class DeadlineMissingDescriptionException extends SundayException {
    public DeadlineMissingDescriptionException() {
        super("Deadline must have a description. Add it in now!");
    }
}
