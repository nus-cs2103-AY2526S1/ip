package steven.task;

/**
 * Represents a task that does not have a date or time attached to it.
 *
 * @author w3njii
 */
public class ToDo extends Task {
    /**
     * Creates a new to-do task with the given description.
     *
     * @param description a description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String convertToSaveFormat() {
        return "[T]" + super.convertToSaveFormat();
    }

    /**
     * {@inheritDoc}
     *
     * Additionally, adds a "[T]" at the front to represent "to-do" task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
