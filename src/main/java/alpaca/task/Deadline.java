package alpaca.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline date.
 * Extends {@link Task}.
 */
public class Deadline extends Task {

    /** The deadline of the task. */
    protected LocalDate by;

    /**
     * Constructs a Deadline task with a description and a date string.
     *
     * @param description The description of the task; must not be null or empty.
     * @param by          The deadline date in the format "yyyy-MM-dd".
     * @throws IllegalArgumentException If the date format is invalid.
     */
    public Deadline(String description, String by) {
        super(description);
        assert description != null && !description.isBlank() : "Description cannot be null or empty";

        try {
            this.by = LocalDate.parse(by); // expects yyyy-MM-dd
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd.", e);
        }
    }

    /**
     * Returns the string representation for saving the task to a file.
     *
     * @return The save format string.
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Returns a user-friendly string representation of the Deadline task.
     *
     * @return The formatted string for display.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
