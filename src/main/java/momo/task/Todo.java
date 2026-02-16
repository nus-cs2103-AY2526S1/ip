package momo.task;

/**
 * Represents a todo task without any date/time constraints.
 * Extends {@link Task}.
 */
public class Todo extends Task {

    /**
     * Creates a new {@code Todo} task with the given description.
     *
     * @param description the description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a new {@code Todo} task with the given description and completion status.
     *
     * @param description the description of the todo task.
     * @param isDone whether the task is marked as done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the todo task,
     * including its type, description, and completion status.
     *
     * @return a formatted string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the todo task to a string format suitable for saving to disk.
     *
     * @return a string in the format {@code "T | <status> | <description>"},
     *         where status is 1 if done, 0 if not done.
     */
    @Override
    public String convertToSaveFormat() {
        return "T | " + super.convertToSaveFormat();
    }
}
