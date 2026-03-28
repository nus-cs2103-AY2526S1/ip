package exceptions;

public class InvalidDateException extends MarkExceptions {

    public InvalidDateException() {
        super("Date format [yyyy-mm-dd] required");
    }
}
