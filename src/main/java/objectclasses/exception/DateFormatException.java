package objectclasses.exception;

/**
 * Represents an invalid date inputted by the user.
 */
public class DateFormatException extends LynxException {

    public DateFormatException() {
        super("Invalid date. Please retry using yyyy-MM-dd-HH-mm.");
    }

}
