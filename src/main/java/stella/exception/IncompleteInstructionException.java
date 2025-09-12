package stella.exception;

/**
 * IncompleteInstructionException is thrown when the user did not
 * provide sufficient information for Stella to respond.
 */
public class IncompleteInstructionException extends StellaException {
    public IncompleteInstructionException(String message) {
        super(message);
    }
}
