/**
 * Exception thrown when a task command is badly formatted,
 * e.g., missing required fields such as /by, /from, or /to.
 */

package salah;

public class TaskFormattingException extends Exception {
    public TaskFormattingException(String message) {
        super(message);
    }
}