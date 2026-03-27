package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import util.ShrekException;

/**
 * Represents a task with a specific deadline date.
 * Extends the base Task class to include a due date functionality.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description the task description
     * @param by          the deadline date string in yyyy-MM-dd format
     * @throws ShrekException if the date format is invalid
     */
    public Deadline(String description, String by) throws ShrekException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd HH:mm")); // convert from string to LocalDate
        } catch (DateTimeParseException e) {
            throw new ShrekException("Shrek needs the date in *year-month-day HH:mm* format, e.g. 2025-01-01 14:30");
        }
    }

    /**
     * Returns the deadline date of this task.
     *
     * @return the LocalDate representing the deadline
     */
    // Add a public getter method
    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof Deadline)) {
            return false;
        }

        Deadline other = (Deadline) obj;
        return this.by.equals(other.by);
    }

    /**
     * Returns a string representation of the Deadline task.
     * Includes task type, status, description, and formatted deadline date.
     *
     * @return formatted string representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")
                .withLocale(java.util.Locale.ENGLISH)) + ")";
    }

    /**
     * Returns the file format representation of the Deadline task for storage.
     * Format: "D | status | description | deadline_date"
     *
     * @return string representation suitable for file storage
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
