package jerome.task;

/**
 * Represents a "to-do" task.
 * Extends the abstract class Task and represents a simple task with a description.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     */
    public Todo(String description) {
        super(description);
    }

    /// Returns a string representation of the to-do task.
    @Override
    public String toString() {
        return "[T][" + this.getStatus() + "] " + this.description;
    }
}
