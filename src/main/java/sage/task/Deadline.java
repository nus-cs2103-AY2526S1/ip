package sage.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 * A Deadline task has a description, a due date/time, and can be marked as done or not done.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the given description and due date/time.
     * @param description The description of the deadline task.
     * @param by The due date and time of the deadline task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task, including its type icon and due date/time.
     * The due date/time is formatted as "MMM dd yyyy, h:mm a".
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }

    /**
     * Gets the due date and time of this deadline.
     * @return The LocalDateTime representing when this deadline is due.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a string representation of the Deadline task suitable for saving to a file.
     * The format is: D | <status> | <description> | <due_date_time>
     * The due date/time is saved in ISO 8601 format.
     * @return A file-formatted string representing the deadline task.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}