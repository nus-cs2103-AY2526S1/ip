package jooh.exception;
/**
 * Exception thrown when an event command is missing required timeline
 * details such as the "/from" or "/to" fields.
 */
public class InvalidEventTimelineException extends JoohException {
    /**
     * Constructs an {@code InvalidEventTimelineException} with a default
     * error message indicating that the event timeline is incomplete.
     */
    public InvalidEventTimelineException() {
        super("From...and to where?");
    }
}
