package hachiware;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task.
 * An Event task has a description, a start date-time, and an end date-time.
 */
public class Event extends Task {
    /** Formatting for the date and time. */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /** Starting time of the Event. */
    protected LocalDateTime from;

    /** Ending time of the Event. */
    protected LocalDateTime to;


    /**
     * Creates a new Event task.
     *
     * @param description description of the event
     * @param from start date-time in {@code yyyy-MM-dd HH:mm} format
     * @param to end date-time in {@code yyyy-MM-dd HH:mm} format
     * @throws HachiwareException if date-time format is invalid
     * @throws IllegalArgumentException if end time is before start time
     */
    public Event(String description, String from, String to) throws HachiwareException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from.trim(), FORMATTER);
            this.to = LocalDateTime.parse(to.trim(), FORMATTER);
        } catch (DateTimeParseException e) {
            throw new HachiwareException("Invalid date & time format. Use yyyy-MM-dd HH:mm");
        }

        if (this.to.isBefore(this.from)) {
            throw new IllegalArgumentException("Ending time cannot be before starting time.");
        }
    }

    /**
     * Returns the string representation of the Event task
     * denoted by [E] in front to represent Event.
     *
     * @return task string with formatted start and end times
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(FORMATTER) + " to: " + to.format(FORMATTER) + ")";
    }
}
