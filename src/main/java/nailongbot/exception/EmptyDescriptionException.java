package nailongbot.exception;

/**
 * Exception thrown when a task is created without a valid description.
 * This exception is a subclass of NaiLongException and is used to
 * indicate that the user has attempted to create a task with an empty
 * or missing description.
 */
public class EmptyDescriptionException extends JkBotException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
