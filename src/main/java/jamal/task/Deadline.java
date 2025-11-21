package jamal.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import jamal.util.DateTime;

/**
 * Deadline Subclass of Task.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Deadline task creation
     *
     * @param description Task description
     * @param by Deadline date
     * @throws DateTimeParseException if by is not of parsable date format "yyyy-mm-ddThh:mm:ss"
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by);
        } catch (DateTimeParseException e) {
            throw e; //prevent creation of deadline object for snowballing errors
        }
    }

    /**
     * Overloaded Deadline task creation
     *
     * @param description Task description
     * @param priority Priority level
     * @param by Deadline date
     * @throws DateTimeParseException if by is not of parsable date format "yyyy-mm-ddThh:mm:ss"
     */
    public Deadline(String description, int priority, String by) throws DateTimeParseException {
        super(description, priority);
        try {
            this.by = LocalDateTime.parse(by);
        } catch (DateTimeParseException e) {
            throw e; //prevent creation of deadline object for snowballing errors
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTime.formatDateTime(by) + ")";
    }

    /**
     * Overdue method returns true if current date is before by date
     *
     * @return true if current date before by date, false otherwise
     */
    @Override
    public boolean isOngoing() {
        return DateTime.isOngoing(by);
    }

    /**
     * Overdue method returns true if current date is before by date
     *
     * @return true if current date after by date, false otherwise
     */
    @Override
    public boolean isOverdue() {
        return DateTime.isOverdue(by);
    }

    public LocalDateTime getBy() {
        return this.by;
    }
}
