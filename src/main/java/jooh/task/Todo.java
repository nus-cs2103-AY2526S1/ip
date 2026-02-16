package jooh.task;

/**
 * Represents a to-do task.
 * A to-do has only a description and completion status,
 * without any associated date or time.
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task with the given description
     * and completion state.
     *
     * @param desc   Description of the to-do task.
     * @param isDone Whether the to-do is marked as completed.
     */
    public Todo(String desc, Boolean isDone) {
        super(desc, isDone);
    }

    /**
     * Returns a string representation of this to-do task,
     * including its type and completion status.
     *
     * @return A user-friendly string representation of the to-do.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
