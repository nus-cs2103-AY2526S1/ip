package exceptions;

/**
 * Thrown when an event command is missing its description.
 */
public class EventMissingDescriptionException extends SundayException {
    public EventMissingDescriptionException() {
        super("Event description cannot be empty. (ovo)");
    }
}
