package cheryl.util;

/**
 * Represents exceptions specific to Duke operations.
 * Thrown when user input is invalid or storage errors occur.
 */

public class DukeException extends Exception {

    /**
     * Creates a new DukeException with the given error message.
     *
     * @param message The error message describing the exception
     */
    public DukeException(String message) {
        super(message);
    }
}