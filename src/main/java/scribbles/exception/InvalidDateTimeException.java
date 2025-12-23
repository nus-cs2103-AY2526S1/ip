package scribbles.exception;

/**
 * Provides exception when receiving an invalid date time from input
 */
public class InvalidDateTimeException extends ScribblesException {
    public InvalidDateTimeException() {
        super("Whoops! Invalid date/time format. Use d/M/yyyy HHmm (e.g. 28/12/2025 1800)");
    }
}
