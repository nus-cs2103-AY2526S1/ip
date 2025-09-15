package chirp.exceptions;

/**
 * Represents base checked exceptions for Chirp Bot
 */
public class ChirpException extends Exception {
    /**
     * Creates ChirpException
     *
     * @param reason Reason for the exception
     */
    public ChirpException(String reason) {
        super(reason);
    }
}
