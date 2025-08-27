package beebong.exception;

public class InvalidDateException extends BBongException {
    public InvalidDateException() {
        super("Invalid Date Provided! Enter in dd/MM/yyyy hh:mm format (time is optional)");
    }

    public InvalidDateException(String message) {
        super(message);
    }
}
