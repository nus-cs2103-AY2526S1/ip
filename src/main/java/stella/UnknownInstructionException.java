package stella;

/**
 * UnknownInstructionException is thrown when
 * user uses a command that does not exist
 */
public class UnknownInstructionException extends Exception {
    public UnknownInstructionException(String message) {
        super(message);
    }
}
