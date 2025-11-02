package jimbot.tasktype;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs over a period of time.
 * Stores the start and end date/time of the event.
 *
 * @author limjimin-nus
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start, and end times.
     *
     * @param description Description of the event.
     * @param from Start date and time of the event.
     * @param to End date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        assert description != null && !description.isEmpty() : "Event description cannot be null or empty";
        assert from != null : "Event start time (from) cannot be null";
        assert to != null : "Event end time (to) cannot be null";
        assert !to.isBefore(from) : "Event end time cannot be before start time";

        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from.toLocalDate();
    }

    public LocalDate getTo() {
        return to.toLocalDate();
    }

    public LocalDateTime getFromDateTime() {
        return from;
    }

    /**
     * Converts a given LocalDateTime into a formatted string.
     * If the time is midnight, only the date is shown; otherwise, date and time are shown.
     *
     * @param dateTime Date and time to format.
     * @return Formatted string representation of the date and time.
     */
    private String dateTimeToString(LocalDateTime dateTime) {
        boolean isMidnight = dateTime.toLocalTime().equals(LocalTime.MIDNIGHT);
        if (isMidnight) {
            return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"));
        }
    }

    /**
     * Returns a string representation of the Event task.
     * Includes the task type, description, and formatted start and end times.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "\n               (FROM: "
                + dateTimeToString(from)
                + " TO: "
                + dateTimeToString(to) + ")";
    }
}
