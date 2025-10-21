package exception;

/**
 * Represents custom exception for DukeGpt.
 * Can be extended for more specific functionalities
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
