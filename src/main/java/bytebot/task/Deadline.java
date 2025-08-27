package bytebot.task;

import bytebot.ByteException;
import bytebot.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that needs to be completed by a specific time.
 */
public class Deadline extends Task {
    private final LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Creates a deadline task.
     *
     * @param description Description of the task
     * @param by Deadline as a string in format "d/M/yyyy HHmm"
     */
    public Deadline(String description, String by) throws ByteException {
        super(description);
        this.by = parseDateTime(by);
    }

    /**
     * Parses a date-time string into LocalDateTime.
     * Supports format: d/M/yyyy HHmm
     *
     * @param dateTimeString The date-time string to parse
     * @return LocalDateTime object
     * @throws ByteException if the date format is invalid
     */
    private LocalDateTime parseDateTime(String dateTimeString) throws ByteException {
        try {
            return LocalDateTime.parse(dateTimeString, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ByteException("Invalid date format, use format: dd/MM/yyyy HHmm");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_FORMATTER) + ")";
    }
}



