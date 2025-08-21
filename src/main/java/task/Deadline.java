package task;

/**
 * Represents a Deadline task.
 * Task with description, due date, and completion status.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Creates a Deadline with the given description and due date.
     *
     * @param description the task details
     * @param by the task due date
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void complete() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
