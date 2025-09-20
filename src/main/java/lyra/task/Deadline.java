package lyra.task;

import java.time.LocalDate;

import lyra.util.DateTimeUtil;

/**
 * Represents a task with a specific deadline date.
 * Extends the base Task class to provide deadline-specific functionality.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructs a new Deadline task with the specified description and deadline date.
     *
     * @param description the description of the deadline task
     * @param by the deadline date for the task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the type code identifier for Deadline tasks.
     *
     * @return "D" representing Deadline task type
     */
    public static String typeCode() {
        return "D";
    }

    /**
     * Gets the deadline date for this task.
     *
     * @return the deadline date
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Converts the Deadline task to a data string format for storage.
     * Format: D | status | description | deadline
     *
     * @return string representation suitable for data storage
     */
    public String toDataString() {
        return String.join(" | ", typeCode(), this.isDone ? "1" : "0", this.description, this.by.format(DateTimeUtil.STORAGE_DATE));
    }

    /**
     * Returns a string representation of the Deadline task.
     * Format: [D][status] description (by: deadline)
     *
     * @return string representation of the Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeUtil.formatForDisplay(by) + ")";
    }
}


