package cortana.exception;

/**
 * Exception specific to cortana.core.Cortana chatbot application. Represents errors or exceptional
 * conditions related to cortana.core.Cortana operations.
 */
public class CortanaException extends Exception {

    /**
     * Creates a new cortana.exception.CortanaException with the specified error message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public CortanaException(String message) {
        super(message);
    }
}
