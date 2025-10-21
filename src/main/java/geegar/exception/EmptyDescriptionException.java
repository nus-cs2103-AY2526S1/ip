package geegar.exception;

/**
 * Thrown to indicate user did not insert description for the task
 * For example when a user types "todo" without providing any description, exception will be thrown
 */
public class EmptyDescriptionException extends GeegarException {

    public EmptyDescriptionException(String taskType) {
        super("Hey! The description of a " + taskType + " cannot be empty.");
    }
}
