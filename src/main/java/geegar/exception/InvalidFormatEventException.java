package geegar.exception;

/**
 * Thrown to indicate the event format was written wrongly
 * Examples could be no "/from" and "/to" indicators, no date/time given
 */
public class InvalidFormatEventException extends GeegarException {

    public InvalidFormatEventException() {
        super("Invalid event format! Use: event <description> /from <start> /to <end>");
    }
}