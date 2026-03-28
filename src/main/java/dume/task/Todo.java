package dume.task;

/**
 * Represents a simple task with no specific time constraints.
 */
public class Todo extends Task {
    /**
     * Creates a new Todo.
     *
     * @param details description of the todo
     */
    public Todo(String details) {
        super(details);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isCompleted ? "1" : "0") + " | " + details;
    }
}
