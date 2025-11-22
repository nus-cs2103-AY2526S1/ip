package exception;

/**
 * Custom exception for GenieWeenie app errors.
 */
public class GenieweenieException extends Exception {

    /**
     * Creates a new GenieweenieException.
     *
     * @param message error message
     */
    public GenieweenieException(String message) {
        super(message);
    }
}
