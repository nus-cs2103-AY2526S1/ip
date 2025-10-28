package exceptions;

/**
 * Throws error if the user input for an event or deadline does not match the given.
 */

public class InvalidDateInput extends Exception {
    protected String message;

    /**
     * Creates the invalidDateInput error.
     */
    public InvalidDateInput() {
        super("Invalid date input, please format as YYYY-DD-MM");
    }
}
