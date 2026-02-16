package dukeychatbot.dukeyexceptions;

/**
 * Constructs EmptyDescriptionException error which inherits from DukeyException.
 *
 * <p>Returns error when tasks description is not stated by users.</p>
 */
public class EmptyDescriptionException extends DukeyException {

    /**
     * Constructs the EmptyDescriptionException object.
     */
    public EmptyDescriptionException() {
        super("WARNING: The description of a task cannot be empty.\n"
              + "Input the command with a description after it as such: todo <task description> "
              + "or deadline <task description>");
    }
}
