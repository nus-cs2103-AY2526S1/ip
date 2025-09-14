package tsundere;

/**
 * Thrown if the thread with timeout function fails to execute.
 */
public class TsundereExitDelayException extends TsundereException {
    public TsundereExitDelayException() {
        super("Error with thread while attempting to shut down application.\n");
    }
}
