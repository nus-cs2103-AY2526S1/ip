package stella.exception;

/**
 * UnknownInstructionException is thrown when
 * user uses a command that does not exist
 */
public class UnknownInstructionException extends StellaException {
    public UnknownInstructionException(String message) {
        super(message);
    }
}
