package steven.exception;

/**
 * Thrown when the user attempts to add a task without providing a description.
 */
public class EmptyDescriptionException extends StevenException {
    public EmptyDescriptionException() {
        super("\tWhr is your description????");
    }
}
