package aurora.task;

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
     * @param isDone the completion status
     * @param by the task due date
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
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

    @Override
    public String toText() {
        return "D|" + super.toText() + String.format("|%s\n", by);
    }
}
