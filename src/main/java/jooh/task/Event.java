package jooh.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Represents an event task that has a start and end time.
 * Stores the timeline as two {@link LocalDateTime} values
 * and provides formatted string representations for display and storage.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    /**
     * Constructs an {@code Event} task with the given description,
     * start time, end time, and completion state.
     *
     * @param desc   Description of the event.
     * @param from   Start time string to parse into a {@link LocalDateTime}.
     * @param to     End time string to parse into a {@link LocalDateTime}.
     * @param isDone Whether the event is marked as completed.
     * @throws IllegalArgumentException If either date string cannot be parsed.
     */
    public Event(String desc, String from, String to, Boolean isDone) {
        super(desc, isDone);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }
    /**
     * Attempts to parse a date/time string using multiple accepted formats.
     *
     * @param date The string to parse into a {@link LocalDateTime}.
     * @return The parsed {@link LocalDateTime}.
     * @throws IllegalArgumentException If none of the expected formats match.
     */
    private LocalDateTime parseDateTime(String date) {
        String[] dateFormats = {
                "yyyy-MM-dd HHmm",
                "MM/dd/yyyy HHmm",
                "yyyy-MM-dd HH:mm",
                "MM/dd/yyyy HH:mm"
        };

        for (String format :dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                return LocalDateTime.parse(date, formatter);
            } catch (DateTimeParseException e) {
                //skip, do nothing here
            }
        }
        throw new IllegalArgumentException("Invalid date format");
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E] " + super.toString() +
                " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
    /**
     * Returns the start time formatted for saving to storage.
     *
     * @return A string representation of the start time in {@code yyyy-MM-dd HHmm} format.
     */
    public String getFrom() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return from.format(formatter);
    }
    /**
     * Returns the end time formatted for saving to storage.
     *
     * @return A string representation of the end time in {@code yyyy-MM-dd HHmm} format.
     */
    public String getTo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return to.format(formatter);
    }
}
