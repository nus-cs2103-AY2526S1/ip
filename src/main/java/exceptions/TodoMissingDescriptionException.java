package exceptions;

/**
 * Thrown when a todo command is missing its description.
 */
public class TodoMissingDescriptionException extends SundayException {
    public TodoMissingDescriptionException() {
        super("Todo cannot be left blank. Please describe your task.");
    }
}
