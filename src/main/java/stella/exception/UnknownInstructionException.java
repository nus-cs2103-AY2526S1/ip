package stella.exception;

/**
 * Throws when user uses a command that does not exist.
 */
public class UnknownInstructionException extends StellaException {
    /**
     * Constructs a new UnknownInstructionException, with the exception message.
     *
     * @param message The exception message.
     */
    public UnknownInstructionException(String message) {
        super(message);
    }
}
