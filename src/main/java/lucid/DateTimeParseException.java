package lucid;

/**
 * Exception resulting from error parsing date and time
 */
public class DateTimeParseException extends LucidException {
    public DateTimeParseException() {
        super("I'm sorry! Something went wrong with the date and time");
    }
}
