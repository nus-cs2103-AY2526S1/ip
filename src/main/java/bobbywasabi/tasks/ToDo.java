package bobbywasabi.tasks;

/**
 * Represents a To-Do task without a specific date/time.
 * Extends the generic Task class.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with a description and marked status.
     *
     * @param description The description of the task.
     * @param isMarked    Whether the task is marked as done.
     */
    public ToDo(String description, boolean isMarked) {
        super(description, isMarked);
    }

    /**
     * Returns the string representation of the ToDo task.
     * Prepends "[T]" to indicate task type.
     *
     * @return String representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string of the task data suitable for saving.
     * Format: T|description|checked_status
     *
     * @return A pipe-separated string representing the ToDo task.
     */
    @Override
    public String getData() {
        return String.format("T|%s|%s",
                super.getDescription(), super.checked());
    }
}
