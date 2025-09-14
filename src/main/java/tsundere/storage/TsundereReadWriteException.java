package tsundere.storage;

import tsundere.TsundereException;

/**
 * Thrown when an exception occurred while storage attempts to read or write data.
 */
public class TsundereReadWriteException extends TsundereException {
    public TsundereReadWriteException() {
        super("An exception occurred when reading/writing to storage.\n");
    }
}
