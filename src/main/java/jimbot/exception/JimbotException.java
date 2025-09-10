package jimbot.exception;

/**
 * Represents exceptions specific to the Jimbot application.
 * All custom exceptions should extend this class.
 */
public class JimbotException extends Exception {
    /**
     * Initializes a new JimbotException with the specified detail message.
     *
     * @param message Detail message describing the exception.
     */
    public JimbotException(String message) {
        super(message);
    }
}
