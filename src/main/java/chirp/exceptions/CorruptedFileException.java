package chirp.exceptions;

/**
 * Represents exception for corrupted task data file
 */
public class CorruptedFileException extends ChirpException {
    /**
     * Creates CorruptedFileException
     *
     * @param reason Type of file corruption
     */
    public CorruptedFileException(String reason) {
        super("Corrupted File. Invalid Entry: " + reason);
    }
}
