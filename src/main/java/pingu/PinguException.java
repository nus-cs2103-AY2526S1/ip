package pingu;

/**
 * Custom exception class for the Pingu application.
 * Used to signal errors specific to the application's logic.
 */
public class PinguException extends Exception {
    public PinguException(String message) {
        super(message);
    }
}