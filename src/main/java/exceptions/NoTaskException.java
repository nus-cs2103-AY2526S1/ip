package exceptions;

/**
 * Exception thrown when no task provided.
 */
public class NoTaskException extends RuntimeException {

    /**
     * Constructs an error
     * Prints specific message
     */
    public NoTaskException() {
        super("Yikes! Candy don't know what to make."
                + "\nInput your task!");
    }
}
