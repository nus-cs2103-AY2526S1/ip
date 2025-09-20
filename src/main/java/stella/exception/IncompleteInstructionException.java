package stella.exception;

/**
 * Throws when the user did not provide sufficient information for Stella to respond.
 */
public class IncompleteInstructionException extends StellaException {
    /**
     * Constructs a new IncompleteInstructionException, with the exception message.
     *
     * @param message The exception message.
     */
    public IncompleteInstructionException(String message) {
        super(message);
    }
}
