package tsundere.parser;

import tsundere.TsundereException;

/**
 * Thrown if the user did not fill in all the fields for the respective task type.
 */
public class TsundereTaskFormatException extends TsundereException {
    public TsundereTaskFormatException() {
        super("Wrong format!\n");
    }
}
