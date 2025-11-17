package beebong.exception;

/**
 * Represents a Missing Save File exception used within the BeeBong application.
 */
public class MissingSaveFileException extends BBongException {
    /**
     * Constructs a new {@code MissingSaveFileException} with a pre-defined message.
     */
    public MissingSaveFileException() {
        super("Save file does not exist!");
    }

    /**
     * Constructs a new {@code MissingSaveFileException} with the specified detail message.
     *
     * @param message the detail message to describe the cause of the exception.
     */
    public MissingSaveFileException(String message) {
        super(message);
    }
}
