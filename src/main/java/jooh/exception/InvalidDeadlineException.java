package jooh.exception;
/**
 * Exception thrown when a deadline command is missing the required
 * "/by" part or has no deadline information provided.
 */
public class InvalidDeadlineException extends JoohException {
    /**
     * Constructs an {@code InvalidDeadlineException} with a default
     * error message indicating that no deadline was provided.
     */
    public InvalidDeadlineException() {
        super("No deadline provided? :(");
    }
}
