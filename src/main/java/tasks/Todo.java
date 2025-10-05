package tasks;

/**
 * Represents a simple to-do task without any date or time component.
 * <p>
 * A {@code Todo} is the most basic type of {@link Task}, storing only a name,
 * completion status, and ID.
 * </p>
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task.
     *
     * @param name   the task description
     * @param marked whether the task is marked as completed
     * @param id     the task’s unique identifier
     */
    public Todo(String name, boolean marked, int id) {
        super(name, marked, id);
    }

    /**
     * Returns a user-readable string representation of this to-do task.
     *
     * @return formatted string in the form {@code [T][X] task_name}
     */
    @Override
    public String toString() {
        return "[T][" + getMarked() + "] " + getName();
    }

    /**
     * Returns the storage format of this to-do task for saving to file.
     *
     * @return pipe-delimited string in the form {@code T|X|task_name}
     */
    @Override
    public String toDataFormat() {
        return "T|" + getMarked() + "|" + getName();
    }
}
