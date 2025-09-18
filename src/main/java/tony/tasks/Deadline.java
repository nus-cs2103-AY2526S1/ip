package tony.tasks;

import java.time.LocalDateTime;

import tony.parsers.DateTimeManager;

/**
 * Represents a deadline task with a description and a due date/time.
 * Inherits common task behavior from {@link Task}.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeManager.formatForDisplay(deadline) + ")";
    }

    public String toDataFormat() {
        return "D | " + (isDone() ? 1 : 0) + " | " + this.command + " | " + DateTimeManager.format(deadline);
    }
}
