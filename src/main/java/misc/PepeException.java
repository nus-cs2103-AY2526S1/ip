package misc;

/**
 * A PepeException is a custom exception for the Pepe Application.
 */
public class PepeException extends Exception {
    /**
     * Constructs a custom exception for the Pepe application with a specified message.
     * @param message A hopefully descriptive exception message
     */
    public PepeException(String message) {
        super(message);
    }
}
