package watermelon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import watermelon.exception.InvalidDateTimeException;

/**
 * Represents a Deadline task with a due date.
 */
public class Deadline extends Task {
    /** The due date & time of the task */
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     */
    public Deadline(String description, String by) throws InvalidDateTimeException {
        super(description);
        this.taskType = "D";
        this.by = stringToDateTime(by);
    }

    /**
     * Constructs a new Deadline task with the specified description, due date and completion status.
     */
    public Deadline(String description, String by, boolean isDone) throws InvalidDateTimeException {
        super(description, isDone);
        this.taskType = "D";
        this.by = stringToDateTime(by);
    }

    public LocalDateTime getBy() {
        return this.by;
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
        return String.format("D | %s | %s | %s", super.getStatusIcon().equals("X") ? 1 : 0, description,
                by.format(DateTimeFormatter.ofPattern("ddMMyyyy HHmm")));
    }

    @Override
    public String toString() {
        String formatted_by = by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return "[D]" + super.toString() + " (by: " + formatted_by + ")";
    }
}

