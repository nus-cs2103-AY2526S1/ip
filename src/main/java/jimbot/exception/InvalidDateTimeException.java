package jimbot.exception;

/**
 * Represents an InvalidDateTime exception that occurs when an invalid date and time format is given.
 */
public class InvalidDateTimeException extends JimbotException {
    /**
     * Constructs an InvalidDateTime exception with the specified error message.
     */
    public InvalidDateTimeException() {
        super("""
                   (つД`)ノ Invalid date or time! Use dd/MM/yyyy hhmm format!!!
                 """);
    }
}
