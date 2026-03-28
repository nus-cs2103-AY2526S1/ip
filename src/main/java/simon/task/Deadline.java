package simon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline. A <code>Deadline</code> object contains a description and a due date/time.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDateTime byDateTime;

    /**
     * Constructs a Deadline task using the inputted description and due date or time string.
     *
     * @param description Description of the deadline task.
     * @param by          Due date or time as a string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDateTime = parseDateTime(by);
    }

    /**
     * Tries to parse the input string into a LocalDateTime using a fixed set of accepted patterns.
     * Returns null if parsing fails for all patterns.
     *
     * @param input Date or time string to be parsed.
     * @return Parsed LocalDateTime, or null if parsing fails.
     */
    private LocalDateTime parseDateTime(String input) {
        String[] patterns = {
                "d/M/yyyy HHmm",
                "d/M/yyyy",
                "yyyy-MM-dd HHmm",
                "yyyy-MM-dd"
        };
        for (String pattern : patterns) {
            try {
                if (pattern.contains("H")) {
                    return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(pattern));
                } else {
                    return LocalDateTime.parse(input + " 0000", DateTimeFormatter.ofPattern(pattern + " HHmm"));
                }
            } catch (DateTimeParseException e) {
            }
        }
        return null;
    }

    /**
     * Returns the due date or time string for this deadline task.
     *
     * @return Due date or time as a string.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns the due date or time as a LocalDateTime object, or null if parsing failed.
     *
     * @return Due date or time as a LocalDateTime, or null if not parsable.
     */
    public LocalDateTime getByDateTime() {
        return byDateTime;
    }

    /**
     * Returns the string representation of the deadline task, including its type, status icon, and due date or time.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        String dateStr = by;
        if (byDateTime != null) {
            dateStr = byDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"));
        }
        return "[D]" + super.toString() + " (by: " + dateStr + ")";
    }
}