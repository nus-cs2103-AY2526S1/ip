package jarvis.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that spans a period of time (an event).
 * <p>
 * An {@code EventsTask} has a description, a start date/time,
 * and an end date/time, indicating when the event takes place.
 * </p>
 */
public class EventsTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an {@code EventsTask}.
     *
     * @param description Description of the event task.
     * @param from The {@link LocalDateTime} representing when the event starts.
     * @param to   The {@link LocalDateTime} representing when the event ends.
     */
    public EventsTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    protected String getTypeIcon() {
        return "[E]";
    }

    /**
     * Returns the string representation of the event task for display.
     * <p>
     * The format includes the type icon, status icon, description,
     * and the start and end date/times.
     * Example: {@code [E][✗] Project meeting (from: 2025-01-10T10:00 to: 2025-01-10T12:00)}.
     * </p>
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the string representation of the event task for storage.
     * <p>
     * The format follows a pipe-delimited structure:
     * {@code E | <status> | <description> | (from: <start> to: <end>)}.
     * Example: {@code E | 0 | Project meeting | (from: Jan 10 2025, 10:00 AM to: Jan 10 2025, 12:00 PM)}.
     * </p>
     *
     * @return A string representation of the event task suitable for storage.
     */
    @Override
    public String toStorageString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "E" + " | " + (isDone ? "1" : "0") + " | " + this.description + " | " + " (from: " + from.format(fmt) + " to: " + to.format(fmt) + ")";
    }
}
