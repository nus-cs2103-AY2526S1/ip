package tsundere.storage;

import tsundere.TsundereException;

/**
 * Thrown if Tsundere program is unable to initialize a storage location for the text file.
 * Displays an error message to the user, informing that data will not be saved.
 */
public class TsundereStorageFileException extends TsundereException {
    public TsundereStorageFileException() {
        super("Failed to initialize text storage.\nUsing temporary storage, tasks will not be saved.\n");
    }
}
