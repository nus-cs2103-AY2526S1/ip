package evansbot.task;

/**
 * Represents a ToDo task with a description and a due date.
 * The due date can be either a valid LocalDate or a raw string if parsing fails.
 */
public class ToDo extends Task {
    protected String by;

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
        assert description != null && !description.isEmpty() : "Event cannot be null or empty";
    }

    /**
     * Constructs a Event task with the specified description and completion status.
     *
     * @param description Description of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
        assert description != null && !description.isEmpty() : "Event cannot be null or empty";
    }

    /**
     * Returns the string representation of the ToDo task, including its description.
     *
     * @return String representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
