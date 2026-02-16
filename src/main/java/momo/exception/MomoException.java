package momo.exception;

/**
 * Represents a custom exception used in the Momo application.
 * Prepends "Nani?!? " to the provided error message.
 */
public class MomoException extends Exception {

    /**
     * Creates a new {@code MomoException} with the specified detail message.
     *
     * @param message the detail message for this exception.
     */
    public MomoException(String message) {
        super("Nani?!? " + message);
    }
}
