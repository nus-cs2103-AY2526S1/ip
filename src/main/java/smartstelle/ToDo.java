package smartstelle;

/**
 * Represents a ToDo task in the SmartStelle application.
 * <p>
 * A ToDo task has a name and completion status but does not have a specific
 * date or time associated with it.
 * Inherits common functionality from the abstract Task class.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the given name and completion status.
     *
     * @param name        The name or description of the ToDo task.
     * @param isCompleted Whether the task is initially marked as completed.
     * @throws StelleException If the task name is empty.
     */
    public ToDo(String name, boolean isCompleted) throws StelleException {
        super(name, isCompleted);
    }

    /**
     * Returns a display string representing this ToDo task.
     * <p>
     * Includes a type identifier "[T]" and the completion status and name from the
     * Task superclass.
     *
     * @return A formatted string for displaying the task.
     */
    @Override
    public String display() {
        return "[T]" + super.display();
    }

    /**
     * Returns a string representing this ToDo task in a format suitable for saving
     * to a file.
     * <p>
     * Format: "T | 0/1 | taskName", where 0 represents incomplete and 1 represents
     * completed.
     *
     * @return A formatted string for saving the task.
     */
    public String toSaveFormat() {
        return "T | " + (this.getIsCompleted() ? "1" : "0") + " | " + this.getName();
    }
}
