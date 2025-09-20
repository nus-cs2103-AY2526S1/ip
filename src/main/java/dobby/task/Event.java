package dobby.task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that spans a time period.
 */
public class Event extends Task implements Serializable {
    private static final long serialVersionUID = 1L;
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Creates an event task with the given description, start time, and end time.
     *
     * @param description Task description.
     * @param start Event start time.
     * @param end Event end time.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert start != null : "Event date 'from' cannot be null";
        assert end != null : "Event date 'to' cannot be null";
    }

    /** Returns a formatted string representation of the event. */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[E] " + super.toString() +
                " (from: " + start.format(outputFormatter) +
                " to: " + end.format(outputFormatter) + ")";
    }
}
