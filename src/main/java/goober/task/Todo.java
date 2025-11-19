package goober.task;

/**
 * Represents a simple to-do item with a description.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo.
     *
     * @param description the description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
