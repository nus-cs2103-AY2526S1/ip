package chirp.exceptions;

/**
 * Represents exception for unknown user command
 */
public class InvalidCommandException extends ChirpException {

    /**
     * Creates InvalidCommandException
     */
    public InvalidCommandException() {
        super("Unknown Command");
    }
}
