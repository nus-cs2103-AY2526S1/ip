package balloon.exception;

/**
 * Represents an exception that is thrown when a task number provided
 * by the user does not exist in the task list.
 */
public class TaskNumberException extends BalloonException {
    public TaskNumberException() {
        super("The given task number does not exist!");
    }
}
