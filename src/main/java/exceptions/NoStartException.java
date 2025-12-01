package exceptions;

/**
 * Exception thrown when no start time provided if needed.
 */
public class NoStartException extends RuntimeException {

    /**
     * Constructs an error
     * Prints specific message
     */
    public NoStartException() {
        super("Oh no! Candy doesn't know when to start "
                + "\nPlease input a start time by '/from (start)");
    }
}
