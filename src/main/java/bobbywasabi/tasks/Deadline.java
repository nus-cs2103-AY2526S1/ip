package bobbywasabi.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a description, marked status, and a deadline date/time.
 * Extends the generic Task class.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline task with description, marked status, and deadline time.
     *
     * @param description The description of the deadline task.
     * @param isMarked    Whether the task is marked as done.
     * @param deadline    The deadline date and time as a LocalDateTime.
     */
    public Deadline(String description, boolean isMarked, LocalDateTime deadline) {
        super(description, isMarked);
        this.deadline = deadline;
    }

    /**
     * Returns a formatted string representing the deadline date and time.
     * Format example: "by: Jan 1 2024 1800"
     *
     * @return The deadline string.
     */
    public String getDeadline() {
        String output = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
        return String.format("by: %s",
                output);
    }

    /**
     * Returns the string representation of the Deadline task.
     * Prepends "[D]" to indicate task type and appends the deadline.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + this.getDeadline();
    }

    /**
     * Returns a formatted string of the deadline data suitable for saving.
     * Format: D|description|checked_status|deadline
     * Deadline formatted as "d/M/yyyy HHmm".
     *
     * @return A pipe-separated string representing the Deadline task.
     */
    @Override
    public String getData() {
        String deadlineOutput = this.deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return String.format("D|%s|%s|%s",
                super.getDescription(), super.checked(), deadlineOutput);
    }

}
