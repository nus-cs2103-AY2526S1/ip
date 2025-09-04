package sam.task;

/**
 * Represents a deadline task in the task management system.
 * A deadline task has a description, completion status, and a due date/time.
 * It extends the base Task class and provides deadline-specific functionality.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Constructs a new Deadline task with the given description and due date.
     * The task is initially marked as not done.
     *
     * @param description The description of the deadline task
     * @param by The due date/time for the task
     */
    public Deadline(final String description, final String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline task with the given description, completion status, and due date.
     *
     * @param description The description of the deadline task
     * @param isDone The initial completion status of the task
     * @param by The due date/time for the task
     */
    public Deadline(final String description, final boolean isDone, final String by) {
        super(description);
        if (isDone) {
            this.markDone();
        }
        this.by = by;
    }

    /**
     * Returns the type indicator for Deadline tasks.
     *
     * @return The string "[D]" representing a Deadline task
     */
    @Override
    protected String kind() {
        return "[D]";
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string containing the task type, status, description, and due date
     */
    @Override
    public String toString() {
        return kind() + status() + " " + description + " (by: " + by + ")";
    }

    /**
     * Returns the raw due date/time string for storage purposes.
     *
     * @return The due date/time string as stored internally
     */
    public String getByRaw() {
        return by;
    }
}
