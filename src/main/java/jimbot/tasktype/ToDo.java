package jimbot.tasktype;

/**
 * Represents a simple task without a specific date or time.
 * Stores only the description of the task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     * Includes the task type and description.
     *
     * @return String representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
