package briar.exception;

/**
 * Represents the exception thrown when a command has a non-numerical input when expecting a number.
 */
public class NonNumberException extends BriarException {

    public NonNumberException(String command) {
        super("Aww, " + command + " only takes in a number! >.<");
    }
}
