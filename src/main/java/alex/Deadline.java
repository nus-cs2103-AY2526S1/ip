package alex;

/**
 * Represents a task of type deadline.
 * A <code>Deadline</code> has a description and a specific due date/time.
 */
public class Deadline extends Task {

    private String by;

    /**
     * Constructs a <code>Deadline</code> task with the specified description and due date/time.
     *
     * @param description Description of the task.
     * @param by The deadline (due date/time) for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task suitable for saving to a file.
     * Format: <code>D / [task state] / [description] / [by]</code>
     *
     * @return File-friendly string representation of the deadline task.
     */
    @Override
    public String toFileString() {
        return "D / " + this.doTaskState() + " / " + this.getDescription() + " / " + this.by;
    }

    /**
     * Returns the string representation of the deadline task suitable for display to the user.
     * Format: <code>[D][task state] description (by: deadline)</code>
     *
     * @return User-friendly string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}

