package dwight.storage;

/** Exception thrown when a saved task file is corrupted or contains invalid data. */
public class CorruptSaveException extends Exception {

    /**
     * Creates a new {@code CorruptSaveException} with a message indicating the corrupted line in
     * the save file.
     *
     * @param line The corrupted line from the save file.
     */
    public CorruptSaveException(String line) {
        super("Save file is corrupted at line '" + line + "'");
    }
}
