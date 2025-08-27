package Exceptions;

public class InvalidDeadlineException extends EvansBotException {
    public InvalidDeadlineException() {
        super("Please give the deadline in the format of 'deadline (description) /by (date)' for example: deadline return book /by Sunday");
    }
}
