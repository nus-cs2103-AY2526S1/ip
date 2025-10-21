package geegar.exception;

/**
 * Thrown to indicate the deadline format was wrong
 * Example could be no "/by' indicator or no date/time given
 */
public class InvalidFormatDeadlineException extends GeegarException {

    public InvalidFormatDeadlineException() {
        super("Invalid deadline format! Use: deadline <description> /by dd/mm/yyyy HHmm");
    }
}


