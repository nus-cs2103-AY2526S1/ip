package exceptions;

/**
 * Thrown when an event command is missing the required {@code /to} parameter.
 */
public class EventMissingToException extends SundayException {
    public EventMissingToException() {
        super("When does it end? Can you tell me? (o u O)");
    }
}
