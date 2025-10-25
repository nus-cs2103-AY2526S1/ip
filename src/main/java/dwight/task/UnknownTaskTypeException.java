package dwight.task;

/** Exception thrown when an unrecognized task type is provided during task creation or parsing. */
public class UnknownTaskTypeException extends Exception {

    /**
     * Creates a new {@code UnknownTaskTypeException} with a message indicating the unrecognized
     * task type.
     *
     * @param type The keyword of the unknown task type.
     */
    public UnknownTaskTypeException(String type) {
        super("Unknown task type specified '" + type + "'");
    }
}
