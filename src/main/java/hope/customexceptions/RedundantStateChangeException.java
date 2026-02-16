package hope.customexceptions;

/**
 * An exception thrown when attempting to change the state of a task redundantly.
 * This exception is used in the Hope chatbot to indicate that a task's state (e.g., marked as done or not done)
 * is already in the requested state, preventing unnecessary state changes.
 */
public class RedundantStateChangeException extends RuntimeException {

    /**
     * Constructs a {@code RedundantStateChangeException} with the specified detail message.
     *
     * @param msg the detail message explaining the reason for the exception
     */
    public RedundantStateChangeException(String msg) {
        super(msg);
    }

}
