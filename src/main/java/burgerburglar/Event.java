package burgerburglar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a start and end time.
 * <p>
 * Extends {@link Task} to include start and end timestamps.
 */
public class Event extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event with description, start time, and end time.
     *
     * @param description the description of the event
     * @param from        the start time of the event
     * @param to          the end time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        assert !description.isBlank() : "Event description cannot be blank";
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event with description, start time, end time, and done status.
     *
     * @param description the description of the event
     * @param from        the start time of the event
     * @param to          the end time of the event
     * @param isDone      whether the event is completed
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        this(description, from, to);
        this.isDone = isDone;
    }

    /** Returns the type icon for event tasks. */
    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    /** Returns a human-readable string representation of the event task. */
    @Override
    public String toString() {
        String fromDisplay = (from != null) ? from.format(OUTPUT_FORMAT) : "unspecified";
        String toDisplay = (to != null) ? to.format(OUTPUT_FORMAT) : "unspecified";
        return getTypeIcon() + getStatusIcon()
                + " " + description
                + " (from: " + fromDisplay + " to: " + toDisplay + ")";
    }

    /** Serializes the event for saving in the storage file. */
    @Override
    public String serialize() {
        String fromString = (from != null) ? from.toString() : "";
        String toString = (to != null) ? to.toString() : "";
        return String.format("E | %s | %s | %s | %s",
                isDone ? "1" : "0",
                description,
                fromString,
                toString);
    }
}
