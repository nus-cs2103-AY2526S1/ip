package watermelon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import watermelon.exception.InvalidDateTimeException;
import watermelon.exception.InvalidInputException;

/**
 * Represents an Event task with a start and end date.
 */
public class Event extends Task {
    /** The start date & time of the task */
    protected LocalDateTime from;
    /** The end date & time of the task */
    protected LocalDateTime to;

    /**
     * Constructs a new Event task with the specified description, start date and end date.
     */
    public Event(String description, String from, String to)
            throws InvalidDateTimeException, InvalidInputException {
        super(description);
        this.taskType = "E";
        this.from = stringToDateTime(from);
        this.to = stringToDateTime(to);

        if (this.from.isAfter(this.to) || this.from.equals(this.to)) {
            throw new InvalidInputException("Start date/time must be before end date/time.");
        }
    }

    /**
     * Constructs a new Event task with the specified description, start date, end date and completion status.
     */
    public Event(String description, String from, String to, boolean isDone)
            throws InvalidDateTimeException, InvalidInputException {
        super(description, isDone);
        this.taskType = "E";
        this.from = stringToDateTime(from);
        this.to = stringToDateTime(to);

        if (this.from.isAfter(this.to) || this.from.equals(this.to)) {
            throw new InvalidInputException("Start date/time must be before end date/time.");
        }
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Converts a string with "ddMMyyyy HHmm" format into a {@link LocalDateTime}.
     * @param input String representing a date & time.
     * @return A {@link LocalDateTime} representing the specified date & time.
     * @throws InvalidDateTimeException If input does not match the "ddMMyyyy HHmm" pattern.
     */
    private LocalDateTime stringToDateTime(String input) throws InvalidDateTimeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(
                    "Invalid date/time format. Please use ddMMyyyy HHmm format! (Eg. 25092025 1800)");
        }
    }

    /**
     * Converts this task to a file format string for storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("E | %s | %s | %s | %s", super.getStatusIcon().equals("X") ? 1 : 0, description,
                from.format(DateTimeFormatter.ofPattern("ddMMyyyy HHmm")),
                to.format(DateTimeFormatter.ofPattern("ddMMyyyy HHmm")));
    }

    @Override
    public String toString() {
        String formatted_from = from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String formatted_to = to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return "[E]" + super.toString() + " (from: " + formatted_from + " to: " + formatted_to + ")";
    }
}
