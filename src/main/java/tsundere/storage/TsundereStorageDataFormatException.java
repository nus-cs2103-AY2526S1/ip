package tsundere.storage;

import tsundere.TsundereException;

/**
 * Thrown if the storage data is corrupted.
 */
public class TsundereStorageDataFormatException extends TsundereException {
    public TsundereStorageDataFormatException() {
        super("Storage data does not match expected format.\n");
    }
}
