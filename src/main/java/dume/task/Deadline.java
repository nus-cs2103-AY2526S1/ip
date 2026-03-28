package dume.task;

import dume.util.DateTimeHelper;

/**
 * Represents a task that must be done by a certain date/time.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Creates a new Deadline.
     *
     * @param details description of the task
     * @param by deadline date/time string
     */
    public Deadline(String details, String by) {
        super(details);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeHelper.convert(by) + ")";
    }

    /**
     * Gets the deadline time for this task.
     *
     * @return the deadline date/time string
     */
    public String getBy() {
        return by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isCompleted ? "1" : "0") + " | " + details + " | " + by;
    }
}
