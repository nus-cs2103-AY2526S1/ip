package ip.exceptions;

/**
 * Exception for when the file formatting is wrong
 */
public class FileCorruptedException extends Exception {
    public FileCorruptedException(String message) {
        super(message);
    }
}
