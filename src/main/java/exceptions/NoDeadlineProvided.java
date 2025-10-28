package exceptions;

/**
 * Throws an error if there is no deadline provided.
 */

public class NoDeadlineProvided extends Exception {
    protected String message;

    /**
     * Creates the NoDeadlineProvided() error if user fails to do so.
     */
    public NoDeadlineProvided() {
        super("No deadline is provided, please add one.");
    }
}
