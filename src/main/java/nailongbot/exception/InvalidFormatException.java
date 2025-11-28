package nailongbot.exception;

/**
 * Exception thrown when a command format is not appropriate.
 * This exception is a subclass of NaiLongException and is used to
 * indicate that the user has attempted a command that does not follow the
 *  format for the specific command.
 */
public class InvalidFormatException extends JkBotException {
    public InvalidFormatException(String message) {
        super(message);
    }
}
