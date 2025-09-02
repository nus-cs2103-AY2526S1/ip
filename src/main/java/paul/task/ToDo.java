package paul.task;

/**
 * A simple To Do task for Paul.
 */
public class ToDo extends Task {

    /**
     * Creates a To Do task from the given description.
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a To Do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of a To Do for saving into a file.
     *
     * @return The To Do task string for saving.
     */
    @Override
    public String toSaveString() {
        return "T" + super.toSaveString();
    }
}