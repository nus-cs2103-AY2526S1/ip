package jeff.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jeff.storage.JeffException;

/**
 * Represents a task with a deadline that must be completed by a specific date
 * and time. Extends the base Task class to include deadline functionality.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    protected LocalDateTime by;

    /**
     * Creates a new Deadline task with the specified description and deadline.
     *
     * @param description the task description
     * @param by the deadline date and time as a string
     * @throws JeffException if the date format is invalid or cannot be parsed
     */
    public Deadline(String description, String by) throws JeffException {
        super(description, "D");

        try {
            this.by = parseDate(by.trim());
        } catch (JeffException e) {
            throw new JeffException(e.getMessage());
        }
    }

    /**
     * Gets the deadline formatted for display to the user.
     *
     * @return the deadline formatted as "MMM d yyyy h:mma"
     */
    public String getBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        return by.format(formatter);
    }

    /**
     * Gets the deadline formatted for storage.
     *
     * @return the deadline formatted as "yyyy-MM-dd HHmm" for storage
     */
    public String getForStorage() {
        return by.format(FORMAT);
    }

    /**
     * Parses a date string into a LocalDateTime object using multiple
     * acceptable formats.
     *
     * @param date the date string to parse
     * @return the parsed LocalDateTime object
     * @throws JeffException if the date format is invalid or cannot be parsed
     */
    private static LocalDateTime parseDate(String date) throws JeffException {

        String[] acceptableFormats = {"d/M/yyyy HHmm", "dd/MM/yyyy HHmm", "yyyy-MM-dd HHmm"};

        for (String format : acceptableFormats) {
            try {
                return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
            } catch (Exception e) {
                // try next format
            }
        }
        throw new JeffException("Invalid date format. Please use the format: yyyy-MM-dd HHmm");
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return formatted string representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + description + " (by: " + getBy() + ")";
    }
}
