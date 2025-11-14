package shef.exception;

/**
 * Exception indicating data being stored wrongly in save file.
 */
public class CorruptFileException extends ShefException {
    public CorruptFileException() {
        super("Corrupt file");
    }
}
