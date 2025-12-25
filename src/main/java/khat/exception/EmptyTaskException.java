package khat.exception;

/**
 * Represents an exception thrown when a task description is empty.
 */
public class EmptyTaskException extends KhatException {

    public EmptyTaskException(String message) {
        super("Task description cannot be empty!");
    }

}
