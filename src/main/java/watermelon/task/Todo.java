package watermelon.task;

/**
 * Represents a Todo task without any time constraints.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task with the specified description.
     */
    public Todo(String description) {
        super(description);
        this.taskType = "T";
    }

    /**
     * Constructs a new Todo task with the specified description and completion status.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
        this.taskType = "T";
    }

    /**
     * Converts this task to a file format string for storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("T | %s | %s", super.getStatusIcon().equals("X") ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
