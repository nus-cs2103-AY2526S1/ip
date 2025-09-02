package beebong.exception;

/**
 * Represents an Invalid Date exception used within the BeeBong application.
 */
public class InvalidDateException extends BBongException {
    /**
     * Constructs a new {@code InvalidDateException} with a pre-defined message.
     */
    public InvalidDateException() {
        super("Invalid Date Provided! Enter in dd/MM/yyyy hh:mm format (time is optional)");
    }

    /**
     * Constructs a new {@code InvalidDateException} with the specified detail message.
     *
     * @param message the detail message to describe the cause of the exception.
     */
    public InvalidDateException(String message) {
        super(message);
    }
}
