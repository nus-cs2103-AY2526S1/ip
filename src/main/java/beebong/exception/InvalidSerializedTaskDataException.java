package beebong.exception;

public class InvalidSerializedTaskDataException extends BBongException {
    public InvalidSerializedTaskDataException() {
        super("Invalid Serialized Task Data!");
    }

    public InvalidSerializedTaskDataException(String message) {
    super(message);
  }
}
