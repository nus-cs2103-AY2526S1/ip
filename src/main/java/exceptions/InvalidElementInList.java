package exceptions;

/**
 * Thorws error if the input does not have a task.
 */

public class InvalidElementInList extends Exception {
    protected String message;

    /**
     * Creates an InvalidElementInList error.
     */
    public InvalidElementInList() {
        super("Please input a task!");
    }
}
