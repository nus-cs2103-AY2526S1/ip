package alpaca.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end time.
 * Extends {@link Task}.
 */
public class Event extends Task {

    /** The start time of the event. */
    protected LocalDateTime from;

    /** The end time of the event. */
    protected LocalDateTime to;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The description of the event; must not be null or empty.
     * @param from        The start time in "yyyy-MM-dd HHmm" format (e.g., 2019-12-02 1800).
     * @param to          The end time in "yyyy-MM-dd HHmm" format.
     * @throws IllegalArgumentException If the date format is invalid.
     */
    public Event(String description, String from, String to) {
        super(description);
        assert description != null && !description.isBlank() : "Description cannot be null or empty";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.from = LocalDateTime.parse(from, formatter);
            this.to = LocalDateTime.parse(to, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid date/time format. Use yyyy-MM-dd HHmm (e.g., 2019-12-02 1800).", e);
        }
    }

    /**
     * Returns the string representation for saving the event to a file.
     *
     * @return The save format string.
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter saveFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + from.format(saveFmt) + " | " + to.format(saveFmt);
    }

    /**
     * Returns a user-friendly string representation of the Event task.
     *
     * @return The formatted string for display.
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFmt = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return "[E]" + super.toString() + " (from: " + from.format(displayFmt)
                + " to: " + to.format(displayFmt) + ")";
    }
}
