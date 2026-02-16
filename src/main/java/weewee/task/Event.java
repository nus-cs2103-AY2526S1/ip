package weewee.task;

import java.time.LocalDateTime;

import weewee.exception.WeeweeException;
import weewee.parser.DateParser;

/** Represents an event task with a start and end date/time. */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an Event task with the given details.
     *
     * @param taskName Name/description of the event.
     * @param start    Start date/time in "YYYY-MM-DD HHmm" format.
     * @param end      End date/time in "YYYY-MM-DD HHmm" format.
     * @throws WeeweeException If the date/time format is invalid.
     */
    public Event(String taskName, String start, String end) throws WeeweeException {
        super(taskName);
        try {
            this.start = DateParser.dateParse(start);
            this.end = DateParser.dateParse(end);
        } catch (Exception e) {
            throw new WeeweeException("Event date/time format is wrong baka >v<! Use YYYY-MM-DD HHmm");
        }
    }

    /**
     * Returns the start date/time of the event in a user-friendly format e.g. Oct 16 2025 2pm.
     *
     * @return The start date/time String in "MMM d YYYY h:mma" format.
     */
    public String getStart() {
        return DateParser.displayFormat(start);
    }

    /**
     * Returns the raw start date/time (strict format for saving) e.g. 2025-10-16 1400.
     *
     * @return The start date/time String in "YYYY-MM-DD HHmm" format.
     */
    public String getRawStart() {
        return DateParser.formatForSave(start);
    }

    /**
     * Returns the end date/time of the event in a user-friendly format e.g. Oct 16 2025 2pm.
     *
     * @return The end date/time String in "MMM d YYYY h:mma" format.
     */
    public String getEnd() {
        return DateParser.displayFormat(end);
    }

    /**
     * Returns the end date/time of the event in a user-friendly format e.g. Oct 16 2025 2pm.
     *
     * @return The end date/time String in "MMM d YYYY h:mma" format.
     */
    public String getRawEnd() {
        return DateParser.formatForSave(end);
    }

    /**
     * Returns a string representation of this event,
     * showing type, status, name, and start/end times.
     *
     * @return The formatted string representation of the event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s %s (from: %s to: %s)",
                this.getIsdone(), super.getTaskName(), getStart(), getEnd());
    }
}
