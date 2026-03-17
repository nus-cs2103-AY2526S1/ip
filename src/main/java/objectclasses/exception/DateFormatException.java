package objectclasses.exception;

/**
 * Represents an invalid date inputted by the user.
 */
public class DateFormatException extends LynxException {

    public DateFormatException() {
        super("I don't recognise such a date. Are you using a sundial? Please retry using yyyy-MM-dd-HH-mm instead.");
    }

}
