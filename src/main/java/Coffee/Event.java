package Coffee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start and end time in the Coffee application.
 * An event task has a description, a completion status, and a defined time range.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an {@code Event} task with the given description and time range.
     * The task is initialized as not done.
     *
     * @param description Description of the event.
     * @param from Start date and time in the format {@code yyyy-MM-dd HHmm}.
     * @param to End date and time in the format {@code yyyy-MM-dd HHmm}.
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to   = LocalDateTime.parse(to, formatter);
    }

    /**
     * Constructs an {@code Event} task with the given description, time range,
     * and completion status.
     *
     * @param description Description of the event.
     * @param from Start date and time in the format {@code yyyy-MM-dd HHmm}.
     * @param to End date and time in the format {@code yyyy-MM-dd HHmm}.
     * @param isDone Completion status of the task ({@code true} if done, otherwise {@code false}).
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to   = LocalDateTime.parse(to, formatter);
        if (isDone) {
            this.markAsDone();
        }
    }

    /**
     * Returns a string representation of the event task,
     * including its type, description, status, and time range.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (from: " + from.format(fmt)
                + " to: " + to.format(fmt) + ")";
    }

    /**
     * Returns a string representation of the event task formatted for file storage.
     *
     * @return File-formatted string representation of the event task.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + super.getStatusIcon() + " | " + description
                + " | " + from.format(fmt) + " " + to.format(fmt);
    }

}
