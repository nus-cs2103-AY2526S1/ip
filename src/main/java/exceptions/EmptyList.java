package exceptions;

/**
 * Throws an error is the user tries to act on an empty list.
 */

public class EmptyList extends Exception {
    protected String message;

    /**
     * Creates an EmptyList error.
     */
    public EmptyList() {
        super("The list is empty, you can not "
                + "delete anything currently");
    }
}

