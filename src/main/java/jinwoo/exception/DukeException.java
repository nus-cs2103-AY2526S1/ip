package jinwoo.exception;

/**
 * Represents a custom exception, Prints the error message when thrown from Ui class.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
