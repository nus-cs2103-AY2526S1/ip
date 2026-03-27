package exceptions;

/**
 * Thrown when a task that is already marked done is asked to be marked again.
 */
public class TaskAlreadyMarkedException extends Exception {
    public TaskAlreadyMarkedException(int n, boolean done) {
        super("Task " + n + " is already marked as " + (done ? "done." : "undone."));
    }
}
