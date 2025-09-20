package lyra.task;

/**
 * Represents a simple todo task with no deadline or time constraints.
 * Extends the base Task class to provide todo-specific functionality.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets the type code identifier for Todo tasks.
     *
     * @return "T" representing Todo task type
     */
    public static String typeCode() {
        return "T";
    }

    /**
     * Converts the Todo task to a data string format for storage.
     * Format: T | status | description
     *
     * @return string representation suitable for data storage
     */
    public String toDataString() {
        return String.join(" | ", typeCode(), this.isDone ? "1" : "0", this.description);
    }

    /**
     * Returns a string representation of the Todo task.
     * Format: [T][status] description
     *
     * @return string representation of the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}


