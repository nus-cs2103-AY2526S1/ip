package horus.tasks;

/**
 * An exception that may be thrown when the user enters an input that cannot be correctly parsed.
 */
public class InvalidInputException extends Exception {
    public InvalidInputException(String m) {
        super(m);
    }
}
