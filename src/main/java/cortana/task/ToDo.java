package cortana.task;

/**
 * Represents a simple cortana.task.ToDo task with no date/time attached.
 */
public class ToDo extends Task {

    /**
     * Constructs a cortana.task.ToDo task with the specified name.
     *
     * @param name The name or description of the cortana.task.ToDo task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the cortana.task.ToDo task, including the task type,
     * completion status, and name.
     *
     * @return A formatted string representing the cortana.task.ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
