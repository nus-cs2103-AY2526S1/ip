package briar.exception;

/**
 * Represents the exception thrown when a command tries to access a task that is not in the list.
 */
public class TaskNotInListException extends BriarException {

    public TaskNotInListException() {
        super("Aww, that's not in your list! >.<");
    }
}
