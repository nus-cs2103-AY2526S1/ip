package luna.tasks;

/**
 * Represents a task with no associated date or time.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo with the given description
     *
     * @param description Description of task
     * @param isDone Status of task, usually initialised to false
     */
    public Todo(String description, boolean isDone) {

        super(description, isDone);
    }

    /**
     * Returns a string representation of the task for display.
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + super.tagToString();
    }

    /**
     * Returns a string representation of the task for saving to a file.
     *
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString() + super.tagToString();
    }
}

