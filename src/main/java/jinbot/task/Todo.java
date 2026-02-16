package jinbot.task;

/**
 * Represents a todo task with a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of this todo task
     * in the format used for saving to disk.
     *
     * @return A save format string for this todo task.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (getIsDone() ? "1" : "0") + " | " + getTaskName()
            + " | " + getPriority();
    }

    /**
     * Returns the string representation of this todo task for display.
     *
     * @return The string containing the task type, status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + " [" + getPriority() + "]";
    }
}
