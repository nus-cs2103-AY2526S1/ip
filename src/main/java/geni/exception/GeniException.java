package geni.exception;
/**
 * Custom exception used in the Geni application.
 * Wraps error messages for invalid commands or actions.
 */


public class GeniException extends Exception {
    /**
     * Creates a {@code GeniException} with the given message.
     *
     * @param msg error message
     */
    public GeniException(String msg) {
        super(msg);
    }
}
