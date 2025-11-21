package david.exception;

/**
 * Throws an exception if the task description from the command is empty.
 */
public class EmptyDescriptionException extends DavidException {
    public EmptyDescriptionException(String type) {
        super("the description of " + type + " cannot be empty.");
    }
}
