package steven.exception;

/**
 * Thrown when a user attempts to add an Event without specifying the start or end time.
 */
public class MissingStartAndEndTimeException extends StevenException {
    public MissingStartAndEndTimeException() {
        super("\tYour event from when to when????");
    }
}
