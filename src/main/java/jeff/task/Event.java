package jeff.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs at a specific date and time. Extends the base
 * Task class to include event timing functionality.
 */
public class Event extends Task {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    protected LocalDateTime at;
    private final String originalAt;

    /**
     * Creates a new Event task with the specified description and event time.
     *
     * @param description the task description
     * @param at the event date and time as a string
     */
    public Event(String description, String at) {
        super(description, "E");
        this.originalAt = at == null ? "" : at.trim();
        this.at = parseDate(this.originalAt);
    }

    /**
     * Gets the event time formatted for display to the user.
     *
     * @return the event time as a string
     */
    public String getAt() {
        if (at == null) {
            return originalAt.isEmpty() ? "invalid date" : originalAt;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm");
        return at.format(formatter);
    }

    /**
     * Gets the event time formatted for storage.
     *
     * @return the event time as a string for storage
     */
    public String getForStorage() {
        if (at == null) {
            return originalAt;
        }
        return at.format(FORMAT);
    }

    /**
     * Parses a date string into a LocalDateTime object using multiple
     * acceptable formats.
     *
     * @param date the date string to parse
     * @return the parsed LocalDateTime object, or null if parsing fails
     * @throws JeffException if the date format is invalid or cannot be parsed
     */
    private static LocalDateTime parseDate(String date) {

        String[] acceptableFormats = {
            "d/M/yyyy HHmm", "dd/MM/yyyy HHmm", "yyyy-MM-dd HHmm",
            "d/M/yyyy HH:mm", "dd/MM/yyyy HH:mm", "yyyy-MM-dd HH:mm"
        };
        for (String format : acceptableFormats) {
            try {
                return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return formatted string representation of the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + description + " (at: " + getAt() + ")";
    }
}
