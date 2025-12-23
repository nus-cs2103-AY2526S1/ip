package scribbles.task;

/**
 * Provides the properties of a to do task.
 */
public class ToDoTask extends Task {
    private static final String LABEL = "T";

    /**
     * Constructs a to do task.
     *
     * @param description Description of the task.
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Constructs a to do task that is either
     * complete or incomplete.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is completed or not.
     */
    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        return "%s | %s".formatted(LABEL, super.encode());
    }

    @Override
    public String toString() {
        return "[%s]%s".formatted(LABEL, super.toString());
    }
}
