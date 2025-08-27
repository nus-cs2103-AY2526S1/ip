package beebong.exception;

public class UnknownCommandException extends BBongException {
    public UnknownCommandException() {
        super("Unknown Command! B. Bong doesn't know what to do...");
    }

    public UnknownCommandException(String message) {
        super(message);
    }
}
