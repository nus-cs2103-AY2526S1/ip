package winnie.exception;

/**
 * Exception thrown when the task number is invalid.
 */
public class InvalidTaskNumberException extends WinnieException {
    public InvalidTaskNumberException(int input, int maxTasks) {
        super("Task number '" + input + "' is invalid. Please enter a number between 1 and " + maxTasks + ".");
    }
}
