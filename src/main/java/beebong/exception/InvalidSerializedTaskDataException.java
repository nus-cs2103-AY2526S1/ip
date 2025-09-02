package beebong.exception;

/**
 * Represents an Invalid Serialized Task Data exception used within the BeeBong application.
 */
public class InvalidSerializedTaskDataException extends BBongException {
    /**
     * Constructs a new {@code InvalidSerializedTaskDataException} with a pre-defined message.
     */
    public InvalidSerializedTaskDataException() {
        super("Invalid Serialized Task Data!");
    }

    /**
     * Constructs a new {@code InvalidSerializedTaskDataException} with the specified detail message.
     *
     * @param message the detail message to describe the cause of the exception.
     */
    public InvalidSerializedTaskDataException(String message) {
        super(message);
    }
}
