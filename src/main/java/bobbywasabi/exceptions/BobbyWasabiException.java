package bobbywasabi.exceptions;

/**
 * Custom exception class for handling user input and parsing errors in BobbyWasabi.
 */
public class BobbyWasabiException extends Exception {
    /**
     * Constructs a new {@code BobbyWasabiException} with the specified detail message.
     *
     * @param msg The detail message.
     */
    public BobbyWasabiException(String msg) {
        super(msg);
    }

}
