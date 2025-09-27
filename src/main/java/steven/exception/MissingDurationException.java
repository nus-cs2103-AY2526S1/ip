package steven.exception;

/**
 * Thrown when a user attempts to add a Fixed Duration task without specifying a duration.
 */
public class MissingDurationException extends StevenException {
    public MissingDurationException() {
        super("\tWhr is your duration???");
    }
}

