package jamal.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import jamal.util.DateTime;

/**
 * Event Subclass of Task.
 */
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Event task creation
     *
     * @param description Task description
     * @param start Event start date
     * @param end Event end date
     * @throws DateTimeParseException if by is not of parsable date format "yyyy-mm-ddThh:mm:ss"
     */
    public Event(String description, String start, String end) throws DateTimeParseException {
        super(description);
        try {
            this.start = LocalDateTime.parse(start);
            this.end = LocalDateTime.parse(end);
        } catch (DateTimeParseException e) {
            throw e; //prevent creation of event object for snowballing errors
        }
    }

    /**
     * Overloaded Event task creation
     *
     * @param description Task description
     * @param priority Priority level
     * @param start Event start date
     * @param end Event end date
     * @throws DateTimeParseException if by is not of parsable date format "yyyy-mm-ddThh:mm:ss"
     */
    public Event(String description, int priority, String start, String end) throws DateTimeParseException {
        super(description, priority);
        try {
            this.start = LocalDateTime.parse(start);
            this.end = LocalDateTime.parse(end);
        } catch (DateTimeParseException e) {
            throw e; //prevent creation of event object for snowballing errors
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + DateTime.formatDateTime(start)
                + " to: " + DateTime.formatDateTime(end) + ")";
    }

    /**
     * Overdue method returns true if current date is before by date
     *
     * @return true if current date is after start date and before end date, false otherwise
     */
    @Override
    public boolean isOngoing() {
        return DateTime.isOngoing(start, end);
    }

    /**
     * Overdue method returns true if current date is before by date
     *
     * @return true if current date before start date, false otherwise
     */
    @Override
    public boolean isUpcoming() {
        return DateTime.isUpcoming(start);
    }

    /**
     * Overdue method returns true if current date is before by date
     *
     * @return true if current date after end date, false otherwise
     */
    @Override
    public boolean isOverdue() {
        return DateTime.isOverdue(end);
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }
}
