package sora.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a {@code Deadline} task that must be completed by a specific date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a {@code Deadline} task with the specified description and deadline.
     *
     * @param description the description of the task.
     * @param by the deadline date and time.
     */
    public Deadline(String description, LocalDateTime by) {
        super(Task.TaskType.DEADLINE, description);
        assert by != null : "Deadline date/time should not be null";
        this.by = by;
    }

    /**
     * Formats the deadline time into a different string.
     *
     * @return the formatted deadline time in the pattern "MMM dd yyyy HHmm".
     */
    public String byToFormat() {
        assert by != null : "Deadline 'by' field must not be null before formatting";
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH));
    }

    /**
     * Returns a string representation of the {@code Deadline} task,
     * including its description, status, and deadline time.
     *
     * @return the formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.byToFormat() + ")";
    }
}
