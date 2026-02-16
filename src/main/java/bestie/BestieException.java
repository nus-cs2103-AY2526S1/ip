package bestie;

/**
 * Domain-specific checked exception for validation errors within Bestie.
 */
public class BestieException extends Exception {
    /**
     * Creates a new exception with the provided message.
     *
     * @param message user-facing explanation of the error
     */
    public BestieException(String message) {
        super(message);
    }
}
