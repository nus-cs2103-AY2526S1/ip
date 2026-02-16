package weewee.task;

import java.time.LocalDateTime;

import weewee.exception.WeeweeException;
import weewee.parser.DateParser;

/** Represents a deadline task with the due date/time. */
public class Deadline extends Task {
    private final LocalDateTime dateTime;

    /**
     * Constructs a deadline task with the given details.
     *
     * @param taskName Name/description of the deadline.
     * @param dateTime    due date/time in "YYYY-MM-DD HHmm" format.
     * @throws WeeweeException If the date/time format is invalid.
     */
    public Deadline(String taskName, String dateTime) throws WeeweeException {
        super(taskName);
        try {
            this.dateTime = DateParser.dateParse(dateTime);
        } catch (Exception e) {
            throw new WeeweeException("Deadline date/time format is wrong baka >v<! Use YYYY-MM-DD HHmm");
        }
    }

    /**
     * Returns the due date/time of the deadline in a user-friendly format e.g. Oct 16 2025 2pm.
     *
     * @return The due date/time String in "MMM d YYYY h:mma" format.
     */
    public String getDate() {
        return DateParser.displayFormat(this.dateTime);
    }

    /**
     * Returns the raw due date/time (strict format for saving) e.g. 2025-10-16 1400.
     *
     * @return The due date/time String in "YYYY-MM-DD HHmm" format.
     */
    public String getRawDate() {
        return DateParser.formatForSave(dateTime); // saving
    }

    /**
     * Returns a string representation of this deadline,
     * showing type, status, name, and due date/time.
     *
     * @return The formatted string representation of the deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", this.getIsdone(), super.getTaskName(), getDate());
    }
}
