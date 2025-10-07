package jarvis.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * <p>
 * A {@code DeadlinesTask} has a description and a specific due date/time
 * by which the task must be completed.
 * </p>
 */
public class DeadlinesTask extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a {@code DeadlinesTask}.
     *
     * @param description Description of the deadline task.
     * @param by The {@link LocalDateTime} representing when the task is due.
     */
    public DeadlinesTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String getTypeIcon() {
        return "[D]";
    }

    /**
     * Returns the string representation of the deadline task for display.
     * <p>
     * The format includes the type icon, status icon, description,
     * and the formatted due date/time.
     * Example: {@code [D][X] Submit report (by: Jan 5 2025, 11:59 PM)}.
     * </p>
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM d yyy, h:mm a");
        return getTypeIcon() + getStatusIcon() + " " + description + " (by: " + by.format(fmt) + ")";
    }

    /**
     * Returns the string representation of the deadline task for storage.
     * <p>
     * The format follows a pipe-delimited structure:
     * {@code D | <status> | <description> | <due date>}.
     * Example: {@code D | 1 | Submit report | Jan 5 2025, 11:59 PM}.
     * </p>
     *
     * @return A string representation of the deadline task suitable for storage.
     */
    @Override
    public String toStorageString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM d yyy, h:mm a");
        return "D" + " | " + (isDone ? "1" : "0") + " | " + this.description + " | " + by.format(fmt);
    }
}
