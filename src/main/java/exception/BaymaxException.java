package exception;

/**
 * Exception thrown when a task is created with an invalid description.
 * This can occur when the description is empty or does not meet the application's requirements.
 */
public class BaymaxException extends Exception {

    /**
     * Constructs a new BaymaxException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public BaymaxException(String message) {
        super(message);
    }

}
