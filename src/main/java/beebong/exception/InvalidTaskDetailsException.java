package beebong.exception;

public class InvalidTaskDetailsException extends BBongException {
    public InvalidTaskDetailsException() {
        super("Invalid Task Details!");
    }
    public InvalidTaskDetailsException(String message) {
        super(message);
    }
}
