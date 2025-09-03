package evansbot.Exceptions;

/**
 * Represents an InvalidDeadline exception that occurs when an invalid Deadline is given.
 */
public class InvalidDeadlineException extends EvansBotException {
    /**
     * Constructs an Invalid Deadline Exception with the specified error message.
     */
    public InvalidDeadlineException() {
        super("Please give the deadline in the format of 'deadline (description) /by (date)'"
               + " for example: deadline return book /by Sunday");
    }
}
