package exceptions;

/**
 * Exception thrown when no end time provided if needed.
 */
public class NoEndException extends RuntimeException {

    /**
     * Constructs an error
     * Prints specific message
     */
    public NoEndException() {
        super("Oh no! Candy doesn't know when to stop!"
                + "\nPlease specify an end time by inputting"
                + " '/by (end)' for deadline task or '/to (end)' for event task");
    }
}
