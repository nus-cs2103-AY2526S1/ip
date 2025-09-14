package tsundere.task;

import tsundere.TsundereException;

/**
 * Thrown if the Unmark command attempts to unmark a task that is not yet marked.
 */
public class TsundereAlreadyUnmarkedException extends TsundereException {
    /**
     * Creates a new Exception with the specified task.
     * @param task The task involved in the exception
     */
    public TsundereAlreadyUnmarkedException(Task task) {
        super("Why'd you even mark it in the first place? Baka!\n" + task.toString() + '\n');
    }
}
