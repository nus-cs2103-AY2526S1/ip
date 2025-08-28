package evansbot.Exceptions;

public class InvalidEventException extends EvansBotException {
    public InvalidEventException() {
        super("Please give the event in the format of 'event (description) /from (start) /to (end)' for example: event meeting /from 2.30pm /to 3.30pm");
    }
}
