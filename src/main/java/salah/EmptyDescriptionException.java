/**
 * Exception thrown when a task command is missing a description.
 */

package salah;

public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
