package beebong.exception;

public class MissingSaveFileException extends BBongException {
    public MissingSaveFileException() {
        super("Save file does not exist!");
    }

    public MissingSaveFileException(String message) {
        super(message);
    }
}
