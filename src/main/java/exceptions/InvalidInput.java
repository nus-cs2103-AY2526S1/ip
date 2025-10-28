package exceptions;

/**
 * Throws error if user input is invalid.
 */

public class InvalidInput extends Exception {
    protected String message;

    /**
     * Creates a new InvalidInput() error.
     */
    public InvalidInput() {
        super("The input is invalid.");
    }
}
