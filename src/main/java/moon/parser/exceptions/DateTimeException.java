package moon.parser.exceptions;

/**
 * Exception thrown when an invalid or inconsistent time value is encountered.
 * <p>
 * Extends {@link moon.parser.exceptions.ParseException} to distinguish time-specific
 * validation errors from other parsing errors.
 * <p>
 * Typical cases where this exception is used include:
 * <ul>
 *   <li>Deadline dates set in the past</li>
 *   <li>Event start times occurring after their end times</li>
 *   <li>Malformed or unsupported time formats</li>
 * </ul>
 */
public class DateTimeException extends ParseException {
    /**
     * Creates a DateTimeException with a message.
     *
     * @param message Description of the error.
     */
    public DateTimeException(String message) {
        super(message);
    }
}
