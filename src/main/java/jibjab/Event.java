package jibjab;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an event task with a specific duration defined by start and end times.
 *
 * @author niyniy123
 */
public class Event extends Task {
    private static final String INPUT_PATTERN = "[dd/MM/yyyy HH:mm][MMM dd yyyy HH:mm]";
    private static final String DISPLAY_PATTERN = "MMM dd yyyy HH:mm";
    private static final String TYPE_PREFIX = "[E]";
    private static final String FROM_PREFIX = " (from: ";
    private static final String TO_SEPARATOR = " to: ";
    private static final String CLOSING_PAREN = ")";

    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    /**
     * Constructs a new Event with the given task description and time period.
     *
     * @param description the description of the event task
     * @param from the starting date and time as a string. Accepted formats:
     *             "dd/MM/yyyy HH:mm" or "MMM dd yyyy HH:mm"
     * @param toDate the ending date and time as a string. Accepted formats:
     *           "dd/MM/yyyy HH:mm" or "MMM dd yyyy HH:mm"
     * @throws java.time.format.DateTimeParseException if the from or to strings
     *         cannot be parsed using the specified formats
     */
    public Event(String description, String from, String toDate) {
        super(description);
        if (from == null || from.isBlank()) {
            throw new IllegalArgumentException("Event start date/time must not be empty");
        }
        if (toDate == null || toDate.isBlank()) {
            throw new IllegalArgumentException("Event end date/time must not be empty");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_PATTERN);
            this.fromDate = LocalDateTime.parse(from.trim(), formatter);
            this.toDate = LocalDateTime.parse(toDate.trim(), formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date/time for event. "
                    + "Use 'dd/MM/yyyy HH:mm' or 'MMM dd yyyy HH:mm'");
        }
        if (!this.toDate.isAfter(this.fromDate)) {
            throw new IllegalArgumentException("Event end must be after start");
        }
    }

    /**
     * Returns a string representation of the Event task.
     * The format includes the event type marker [E], the task status and description
     * and the formatted from/to times.
     *
     * Example output: "[E][X] Team meeting (from: Dec 25 2024 14:30 to: Dec 25 2024 16:00)"
     *
     * @return a formatted string representation of the event including its type marker,
     *         status, description, and time period in "MMM dd yyyy HH:mm" format
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString()
                + " (from: " + fromDate.format(formatter) + " to: " + toDate.format(formatter) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        Event e = (Event) o;
        return Objects.equals(this.fromDate, e.fromDate)
                && Objects.equals(this.toDate, e.toDate);
    }
}
