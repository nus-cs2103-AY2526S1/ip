package karl.task;

/**
 * Represents a Todo type task without any date.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task with description.
     * @param description task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string representation prefixed with [T].
     * @return string representation
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
