package aries;

/**
 * Custom exception class for Aries application.
 * Used to handle application-specific errors.
 */
public class AriesException extends Exception {
    public AriesException(String message) {
        super(message);
    }
}
