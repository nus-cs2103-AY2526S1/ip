package jooh.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Represents a task with a specific deadline.
 * Stores the due date/time as a {@link LocalDateTime} and
 * provides formatted string representations for display and storage.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;
    /**
     * Constructs a {@code Deadline} task with the given description,
     * deadline string, and completion state.
     *
     * @param desc     Description of the task.
     * @param deadline Deadline string to parse into a {@link LocalDateTime}.
     * @param isDone   Whether the task is marked as completed.
     * @throws IllegalArgumentException If the deadline string cannot be parsed.
     */
    public Deadline(String desc, String deadline, Boolean isDone) {
        super(desc, isDone);
        this.deadline = parseDateTime(deadline);
    }
    /**
     * Attempts to parse a deadline string using multiple accepted formats.
     *
     * @param deadline The string to parse into a {@link LocalDateTime}.
     * @return The parsed {@link LocalDateTime}.
     * @throws IllegalArgumentException If none of the expected formats match.
     */
    private LocalDateTime parseDateTime(String deadline) {
        String[] dateFormats = {
                "yyyy-MM-dd HHmm",
                "MM/dd/yyyy HHmm",
                "yyyy-MM-dd HH:mm",
                "MM/dd/yyyy HH:mm"
        };
        for (String format :dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                return LocalDateTime.parse(deadline, formatter);
            } catch (DateTimeParseException e) {
                //skip, do nothing here
            }
        }
        throw new IllegalArgumentException("Invalid date format");
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyy HH:mm");
        return "[D] " + super.toString() + " (by: " + deadline.format(formatter) + ")";
    }
    /**
     * Returns the deadline formatted for saving to storage.
     *
     * @return A string representation of the deadline in {@code yyyy-MM-dd HHmm} format.
     */
    public String getDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return deadline.format(formatter);
    }
}
