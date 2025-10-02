package bobby.task;

/**
 * Represents a todo task with a description.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description Description of the todo task.
     */
    public ToDo(final String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task for display.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Returns a string representation of the todo task for saving to file.
     *
     * @return Save format string for the todo task.
     */
    @Override
    public String toSaveFormat() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }
}
