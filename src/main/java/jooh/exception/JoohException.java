package jooh.exception;
/**
 * Base exception type for all custom exceptions in the Jooh application.
 * Provides a unified way to signal recoverable, user-facing errors.
 */
public class JoohException extends Exception {
    /**
     * Constructs a {@code JoohException} with the specified detail message.
     *
     * @param msg The error message to display to the user.
     */
    public JoohException(String msg) {
        super(msg);
    }
}
