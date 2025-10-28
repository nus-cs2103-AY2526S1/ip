package exceptions;

/**
 * This error is supposedly thrown if there are duplicate objects.
 */

public class DuplicationError extends Exception {
    protected String message;

    /**
     * Creates a DuplicateError in this method.
     */
    public DuplicationError() {
        super("The list is empty, you can not "
                + "delete anything currently");
    }
}

