package BobbyWasabi.Exceptions;

/**
 * Custom exception class for BobbyWasabi application-specific errors.
 * Used to signal exceptions related to domain logic or validation failures within the app.
 */
public class BobbyWasabiException extends Exception {

    /**
     * Constrcuts a new {@code BobbyWasabiException} with the specified detail message.
     *
     * @param msg The detail message explaining the reason for the exception.
     */
    public BobbyWasabiException(String msg) {
        super(msg);
    }

}
