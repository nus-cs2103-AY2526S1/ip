package jimbot.exception;

/**
 * Represents an InvalidEvent exception that occurs when an invalid Event format is given.
 */
public class InvalidEventException extends JimbotException {
    /**
     * Constructs an InvalidEvent exception with the specified error message.
     */
    public InvalidEventException() {
        super("""
                  Invalid event format...   (￣ー￣)
                  Make sure you've /from (date/time) AND /to (date/time)...
                """);
    }
}
