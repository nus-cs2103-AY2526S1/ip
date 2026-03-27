package exceptions;

/**
 * Thrown when an event command is missing the required {@code /from} parameter.
 */
public class EventMissingFromException extends SundayException {
    public EventMissingFromException() {
        super("When is the start date and time?");
    }
}
