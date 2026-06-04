package sora.task;

/**
 * Represents a {@code Todo} task that has only a description.
 */
public class Todo extends Task {
    /**
     * Constructs a {@code Todo} task with the specified description.
     *
     * @param description the description of the todo task.
     */
    public Todo(String description) {
        super(TaskType.TODO, description);
    }

    /**
     * Returns the string representation of the {@code Todo} task,
     * including its type, status, and description.
     *
     * @return the formatted string representing the todo task.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
