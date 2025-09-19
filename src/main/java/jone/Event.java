package jone;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a scheduled event with a start and end time.
 * An {@code Event} has a description, time range, and completion status.
 */
public class Event extends Task {
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HHmm";
    private static final String DATETIME_FORMAT_ERROR =
            "Invalid datetime format. Please use yyyy-MM-dd HHmm.";

    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs a new {@code Event} with description, start, and end time.
     *
     * @param description The description of the event.
     * @param start Start datetime in yyyy-MM-dd HHmm format.
     * @param end End datetime in yyyy-MM-dd HHmm format.
     * @throws JoneException If the datetime format is invalid.
     */
    public Event(String description, String start, String end) throws JoneException {
        super(description);
        try {
            this.start = LocalDateTime.parse(start,
                    DateTimeFormatter.ofPattern(DATETIME_FORMAT));
            this.end = LocalDateTime.parse(end,
                    DateTimeFormatter.ofPattern(DATETIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new JoneException(DATETIME_FORMAT_ERROR);
        }
    }

    /**
     * Constructs an {@code Event} with description, start, end, and completion status.
     *
     * @param description The description of the event.
     * @param start Start datetime in yyyy-MM-dd HHmm format.
     * @param end End datetime in yyyy-MM-dd HHmm format.
     * @param done Whether the event task has been completed.
     * @throws JoneException If the datetime format is invalid.
     */
    public Event(String description, String start, String end, boolean done) throws JoneException {
        super(description, done);
        try {
            this.start = LocalDateTime.parse(start,
                    DateTimeFormatter.ofPattern(DATETIME_FORMAT));
            this.end = LocalDateTime.parse(end,
                    DateTimeFormatter.ofPattern(DATETIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new JoneException(DATETIME_FORMAT_ERROR);
        }
    }

    /**
     * Returns the string representation of this event.
     *
     * @return A formatted string showing task type, status, description, and time range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"))
                + " to: "
                + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }

    /**
     * Converts this event into a string suitable for saving to file.
     *
     * @return Save format string for this event.
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (isDone() ? 1 : 0) + " | " + getDescription()
                + " | " + start.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT))
                + " | " + end.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }
}
