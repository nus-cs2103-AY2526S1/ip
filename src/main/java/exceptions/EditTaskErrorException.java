package exceptions;

/**
 * Exception thrown when an invalid task input is provided.
 */
public class EditTaskErrorException extends RuntimeException {

    /**
     * Constructs an error
     * Prints specific message
     */
    public EditTaskErrorException() {
        super("Oh no! Candy don't have that sweet \uD83D\uDE22"
                + "\nPlease give candy a valid number");
    }
}
