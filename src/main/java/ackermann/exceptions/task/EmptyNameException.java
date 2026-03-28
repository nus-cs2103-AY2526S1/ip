package ackermann.exceptions.task;

/**
 * Exception to handle null name inputs
 */
public class EmptyNameException extends InvalidTaskException {
    public EmptyNameException() {
        super("Name cannot be empty or blanks!");
    }
}
