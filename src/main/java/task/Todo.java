package task;

/**
 * Represents a simple todo task without any date/time constraints.
 * Extends the base Task class for basic task functionality.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description the todo task description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); // Todos only use description comparison
    }

    /**
     * Returns a string representation of the Todo task.
     * Includes task type, status, and description.
     *
     * @return formatted string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the file format representation of the Todo task for storage.
     * Format: "T | status | description"
     *
     * @return string representation suitable for file storage
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
