package sam.task;

/**
 * Represents a simple todo task in the task management system.
 * A todo task is a basic task with just a description and completion status.
 * It extends the base Task class and provides a specific type indicator.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new Todo task with the given description and completion status.
     *
     * @param description The description of the todo task
     * @param isDone The initial completion status of the task
     */
    public Todo(String description, boolean isDone) {
        super(description);
        if (isDone) {
            this.markDone();
        }
    }

    /**
     * Returns the type indicator for Todo tasks.
     *
     * @return The string "[T]" representing a Todo task
     */
    @Override
    protected String kind() {
        return "[T]";
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return super.isDone();
    }

    /**
     * Returns a formatted string representation of the task for storage.
     *
     * @return A string in the format "T | 1 | description" or "T | 0 | description"
     */
    public String toSaveFormat() {
        return String.format("T | %d | %s", isDone() ? 1 : 0, description);
    }
}
