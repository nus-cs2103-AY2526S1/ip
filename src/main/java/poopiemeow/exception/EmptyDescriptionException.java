package poopiemeow.exception;

/**
 * Exception thrown when a task description is empty or contains only whitespace.
 * This exception is used throughout the PoopieMeow application to ensure that
 * all tasks have meaningful descriptions.
 *
 * @author tch1001
 * @version 1.0
 */
public class EmptyDescriptionException extends Exception {

    /**
     * Constructs a new EmptyDescriptionException with the specified detail message.
     * The message should explain why the exception occurred and provide guidance
     * on how to resolve the issue.
     *
     * @param message the detail message explaining the exception
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
