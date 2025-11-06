package v.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import v.enums.TaskType;

/**
 * A task that needs to be done before a specific date/time.
 * A promise with a deadline, a race against time itself.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected final LocalDate by;

    /**
     * Creates a new Deadline task with description and due date.
     *
     * @param description The purpose of this task.
     * @param by The date when this task must be completed by (in yyyy-MM-dd format).
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the type of this task.
     *
     * @return The TaskType.DEADLINE enum value.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    /**
     * Returns a string representation of this deadline task.
     *
     * @return Formatted string with deadline details.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_FORMAT) + ")";
    }

    /**
     * Returns the serialized representation of this deadline for persistence.
     * Format: {@code D | <done:1|0> | <description> | <yyyy-MM-dd>}.
     *
     * @return A save-friendly string for this deadline.
     */
    @Override
    public String toSaveString() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by.toString();
    }
}
