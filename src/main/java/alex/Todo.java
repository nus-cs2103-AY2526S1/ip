package alex;

/**
 * Represents a Todo task, which is a task that only has a description
 * without a specific date or time.
 */
public class Todo extends Task {

    /**
     * Constructs a <code>Todo</code> task with the given description.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the task suitable for saving to disk.
     *
     * @return String formatted as "T / taskState / description".
     */
    @Override
    public String toFileString() {
        return "T / " + this.doTaskState() + " / " + this.getDescription();
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return String with "[T]" prefix followed by the task details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
