package whchenyicn.task;

/**
 * A ToDo task with a description.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo Task with description.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toSave() {
        return String.format("T | %d | %s", getDoneFlag(), getDesc());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
