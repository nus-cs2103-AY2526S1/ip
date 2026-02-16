package angus.task;

/**
 * Represents a regular task.
 * <p>
 * A ToDo stores a description for the task.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo task.
     * @param description The description of the new ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String saveTask() {
        return "T//" + super.getCompleteStatus() + "//" + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
