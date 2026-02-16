package util;

/**
 * Custom exception class for TronaldDump application.
 * Used to handle application-specific errors with custom messages.
 */
public class TronaldDumpException extends RuntimeException {

    /**
     * Constructor for TronaldDumpException.
     * @param message The error message
     */
    public TronaldDumpException(String message) {
        super(message);
    }
}


