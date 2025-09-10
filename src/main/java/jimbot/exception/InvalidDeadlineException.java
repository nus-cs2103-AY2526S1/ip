package jimbot.exception;

/**
 * Represents an InvalidDeadline exception that occurs when an invalid Deadline format is given.
 */
public class InvalidDeadlineException extends JimbotException {
    /**
     * Constructs an InvalidDeadline exception with the specified error message.
     */
    public InvalidDeadlineException() {
        super("""
                 Invalid deadline format! Make sure you include /by (date/time)!!!
                 (╯°□°）╯︵ ┻━┻
                """);
    }
}
