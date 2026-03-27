package exceptions;

/**
 * Thrown when the parser cannot recognise the user's command.
 */
public class UnknownException extends SundayException {
    public UnknownException() {
        super("Hey! That is not a command I know. Try again! (^.v.^)");
    }
}
