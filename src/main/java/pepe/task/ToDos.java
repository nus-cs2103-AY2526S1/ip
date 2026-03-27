package pepe.task;

/**
 * Represents a ToDo task.
 * <p>
 * A ToDo task has a name and a marked status, but no specific date or deadline.
 */
public class ToDos extends Task {

    /**
     * Constructs a new ToDo task with the given name.
     *
     * @param name the name or description of the task
     */
    public ToDos(String name) {
        super(name);
        assert name != null && !name.isBlank() : "ToDo task name should not be null or empty";
    }

    /**
     * Returns a string representation of the ToDo task for display.
     * <p>
     * Format: [T][X] taskName if marked, [T][ ] taskName if unmarked.
     *
     * @return a human-readable string representing the ToDo task
     */
    @Override
    public String toString() {
        assert super.getName() != null && !super.getName().isBlank()
                : "ToDo task name should not be null or empty for display";
        return "[T]" + super.toString();
    }
    /**
     * Returns a string representing the ToDo task in a file-friendly format.
     * <p>
     * Format: T | 1 | taskName (if marked) or T | 0 | taskName (if unmarked)
     *
     * @return the ToDo task formatted for saving to a file
     */
    @Override
    public String toFileFormat() {
        assert super.getName() != null && !super.getName().isBlank()
                : "ToDo task name should not be null or empty for file format";
        return "T" + " | " + super.checkMarked() + " | " + super.getName();
    }
}
