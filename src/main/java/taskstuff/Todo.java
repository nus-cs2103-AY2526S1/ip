package taskstuff;

/**
 * Represents a todo task with only a description and completion status.
 * This is the simplest type of task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the given description.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation suitable for saving to a data file.
     *
     * @return Data file string for this todo task.
     */
    @Override
    public String toDataFile() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the todo task for display.
     *
     * @return Display string for this todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
