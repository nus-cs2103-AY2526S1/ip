package tsundere.task;

import tsundere.TsundereException;

/**
 * Thrown if the Mark command attempts to mark a task that is already marked.
 */
public class TsundereAlreadyMarkedException extends TsundereException {
    /**
     * Creates a new Exception with the specified task.
     * @param task The task involved in the exception
     */
    public TsundereAlreadyMarkedException(Task task) {
        super("Hey!! It's already marked!\n" + task.toString() + '\n');
    }
}
