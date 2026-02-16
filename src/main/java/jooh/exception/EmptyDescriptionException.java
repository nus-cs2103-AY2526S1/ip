package jooh.exception;
/**
 * Exception thrown when the user provides an empty description
 * for a task-creating command (e.g., todo, deadline, event).
 */
public class EmptyDescriptionException extends JoohException {
    /**
     * Constructs an {@code EmptyDescriptionException} with a message
     * indicating which command type had a missing description.
     *
     * @param event The name of the command that required a description.
     */
    public EmptyDescriptionException(String event) {
        super("The description of a " + event + " cannot be empty");
    }
}
