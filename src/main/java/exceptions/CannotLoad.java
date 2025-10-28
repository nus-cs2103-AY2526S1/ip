package exceptions;

/**
 * CannotLoad is thrown if the list can not be loaded from the document.
 */
public class CannotLoad extends Exception {
    protected String message;

    /**
     * Creates a CannotLoad Error.
     * This is only thrown if it does not work.
     *
     */
    public CannotLoad() {
        super("Unable to load list.");
    }
}
