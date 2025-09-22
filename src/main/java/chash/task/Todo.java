package chash.task;

/** Represents a todo task. */
public class Todo extends Task {
    public static final String TASKTYPE = "T";

    /**
     * Creates a new {@code Todo} task.
     *
     * @param description Task description
     */
    public Todo(String description) {
        super(description);
    }

    /** {@inheritDoc} */
    @Override
    public String exportString() {
        return String.format(
                "%s | %s",
                Todo.TASKTYPE,
                super.exportString()
        );
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format(
                "[%s]%s",
                Todo.TASKTYPE,
                super.toString()
        );
    }
}
