/**
 * Represents a task with a specific deadline.
 * Inherits from Task class and adds a deadline date/time.
 */
public class Deadline extends Task {
    /** The deadline by which the task should be completed */
    public String by;

    /**
     * Creates a new Deadline task with the given description and deadline.
     *
     * @param description The description of the deadline task
     * @param by The deadline by which the task should be completed
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}