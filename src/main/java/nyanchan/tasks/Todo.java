package nyanchan.tasks;

/**
 * Represents a simple to-do task without a date.
 */
public class Todo extends Task {

    /**
     * Creates a new to-do task.
     *
     * @param description task details
     */
    public Todo(String description) {
        super(description);
    }

    /** @return string representation of the to-do */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
