package nailongbot.exception;

/**
 * Exception thrown when a trying to access a task number not on the list
 * This exception is a subclass of NaiLongException and is used to
 * indicate that the user has attempted to find a task number not in the list.
 */
public class InvalidTaskNumberException extends JkBotException {
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
