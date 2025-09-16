package tinkerton.core;

/**
 * Represents an exception specific to the Tinkerton application.
 */
public class TinkertonException extends Exception {
    /**
     * Constructs a TinkertonException with the specified detail message.
     *
     * @param message The detail message.
     */
    public TinkertonException(String message) {
        super(message);
    }
}
