package izayoi.exception;

/**
 * Represents an exception caused by requesting changes to tasks outside of the task list range
 */
public class EmptyArgumentException extends IzayoiException {

    /**
     * Initializes a new TaskRangeException with a complaint
     *
     * @param message the message to display, preferably sassier than usual
     */
    public EmptyArgumentException(String message) {
        super("How did you manage to cut out mid-sentence? " + message);
    }
}
