package bytebot.task;

import bytebot.ByteException;
import bytebot.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that has a start and end time.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Creates an event task.
     *
     * @param description Description of the event
     * @param from Start time as a string in format "d/M/yyyy HHmm" 
     * @param to End time as a string in format "d/M/yyyy HHmm" 
     */
    public Event(String description, String from, String to) throws ByteException {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    /**
     * Parses a date-time string into LocalDateTime.
     * Supports format: d/M/yyyy HHmm (e.g., "2/12/2019 1800")
     *
     * @param dateTimeString The date-time string to parse
     * @return LocalDateTime object
     * @throws ByteException if the date format is invalid
     */
    private LocalDateTime parseDateTime(String dateTimeString) throws ByteException {
        try {
            return LocalDateTime.parse(dateTimeString, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ByteException("Invalid date format. Please use format: dd/MM/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }

    /**
     * Gets the end time as LocalDateTime.
     *
     * @return LocalDateTime of the end time
     */
    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DISPLAY_FORMATTER) + " to: " + to.format(DISPLAY_FORMATTER) + ")";
    }
}



