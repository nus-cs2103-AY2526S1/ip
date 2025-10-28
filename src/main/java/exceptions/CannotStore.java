package exceptions;

/**
 * This error is thrown if the contents can not be stored.
 */

public class CannotStore extends Exception {
    protected String message;

    /**
     * This creates a CannotStore error.
     */
    public CannotStore() {
        super("Unable to store content.");
    }
}
