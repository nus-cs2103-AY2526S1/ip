package steven.exception;

/**
 * Thrown when a user attempts to add a Deadline task without specifying a deadline.
 */
public class MissingDeadlineException extends StevenException {
    public MissingDeadlineException() {
        super("\tWhr is your deadline???");
    }
}
