package nailongbot.exception;

/**
 * Exception thrown when a command is not recognised.
 * This exception is a subclass of NaiLongException and is used to
 * indicate that the user has attempted a command that is not recognised.
 */
public class InvalidCommandException extends JkBotException {
    public InvalidCommandException(String message) {
        super(message);
    }
}

