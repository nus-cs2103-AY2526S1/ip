package duke;
/**
 * Represents an exception specific to Penguin.
 * Exception thrown when an error occurs during the execution of the application.
 */
public class PenguinException extends Exception {
    /**
     * Creates a new PenguinException with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception
     */
    public PenguinException(String message) {
        super(message);
    }
}
