package evansbot.Exceptions;

/**
 * Represents an InvalidEvent exception that occurs when an invalid Event is given.
 */
public class InvalidEventException extends EvansBotException {
    /**
     * Constructs an Invalid Event Exception with the specified error message.
     */
    public InvalidEventException() {
        super("Please give the event in the format of 'event (description) /from (start) /to (end)' "
                + "for example: event meeting /from 2.30pm /to 3.30pm");
    }
}
