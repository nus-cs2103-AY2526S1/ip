package steven.exception;

/**
 * Thrown when the user does not follow the format for date and time when adding a task that requires it.
 */
public class InvalidDateAndTimeFormatException extends StevenException {

    /**
     * Creates a new instance of an InvalidDateAndTimeFormatException.
     */
    public InvalidDateAndTimeFormatException() {
        super("\tPls use the correct format for date and time, the format is dd-mm-yyyy tttt\n\t"
                + "dd: day\n\t"
                + "mm: month\n\t"
                + "yyyy: year\n\t"
                + "tttt: time in 24-hour format, do not use colon (:)");
    }
}
