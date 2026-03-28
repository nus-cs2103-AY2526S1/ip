package taskstuff;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents an event task with a start and end time.
 * Stores both the string and parsed LocalDateTime for each time.
 */
public class Event extends Task {
    /** String representation of the start time. */
    protected String fromString;
    /** String representation of the end time. */
    protected String toString;
    /** Parsed LocalDateTime of the start time. */
    protected LocalDateTime from;
    /** Parsed LocalDateTime of the end time. */
    protected LocalDateTime to;

    /**
     * Constructs an Event task with description, start, and end time.
     *
     * @param description Description of the event.
     * @param fromString Start time as a string (ISO_LOCAL_DATE_TIME format).
     * @param toString End time as a string (ISO_LOCAL_DATE_TIME format).
     */
    public Event(String description, String fromString, String toString) {
        super(description);
        this.fromString = fromString;
        this.toString = toString;
        this.from = LocalDateTime.parse(fromString);
        this.to = LocalDateTime.parse(toString);
    }

    /**
     * Returns a string representation suitable for saving to a data file.
     *
     * @return Data file string for this event.
     */
    @Override
    public String toDataFile() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns a string representation of the event for display.
     *
     * @return Display string for this event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + " to: "
                + this.to.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + ")";
    }
}
