package jone;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * A {@code Deadline} has a description, due date, and completion status.
 */
public class Deadline extends Task {
    private static final String DATE_FORMAT_ERROR = "Invalid date format. Please use yyyy-MM-dd.";

    private final LocalDate by;

    /**
     * Constructs a new {@code Deadline} with the given description and due date.
     *
     * @param description The description of the task.
     * @param by The deadline date in yyyy-MM-dd format.
     * @throws JoneException If the date format is invalid.
     */
    public Deadline(String description, String by) throws JoneException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new JoneException(DATE_FORMAT_ERROR);
        }
    }

    /**
     * Constructs a {@code Deadline} with description, due date, and completion status.
     *
     * @param description The description of the task.
     * @param by The deadline date in yyyy-MM-dd format.
     * @param done Whether the deadline task has been completed.
     * @throws JoneException If the date format is invalid.
     */
    public Deadline(String description, String by, boolean done) throws JoneException {
        super(description, done);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new JoneException(DATE_FORMAT_ERROR);
        }
    }

    /**
     * Returns the string representation of this deadline.
     *
     * @return A formatted string showing task type, status, description, and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Converts this deadline into a string suitable for saving to file.
     *
     * @return Save format string for this deadline.
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (isDone() ? 1 : 0) + " | " + getDescription() + " | " + by;
    }
}
