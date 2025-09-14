package tsundere.log;

import tsundere.TsundereException;

/**
 * Thrown if user attempts an undo command but there are no more commands in logs to undo.
 */
public class TsundereNothingToUndoException extends TsundereException {
    public TsundereNothingToUndoException() {
        super("There's nothing else to undo!");
    }
}
