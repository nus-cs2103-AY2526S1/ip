package benn.tasks;

import benn.exceptions.BennException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline in Benn the Chatbot.
 *
 * <p>A {@code Deadline} has a description, a due date/time, and
 * a completion status. The due date/time is stored internally as
 * a {@link LocalDateTime} and must be provided in the format
 * {@code dd/MM/yyyy HH:mm}. For example: {@code 12/12/2069 14:50}.</p>
 *
 * <p>When serialized to storage, the deadline uses the format
 * {@code dd/MM/yyyy'T'HH:mm} to ensure unambiguous parsing.</p>
 */
public class Deadline extends Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm");
    private final LocalDateTime datetimeDue;

    /**
     * Constructs a new {@code Deadline} task with the specified description,
     * due date/time, and completion status.
     *
     * @param description the description of the task
     * @param datetimeDue the due date/time string in {@code dd/MM/yyyy HH:mm} format
     * @param isDone whether the task is marked as completed
     * @throws BennException if the {@code datetimeDue} string is invalid or not in the expected format
     */
    public Deadline(String description, String datetimeDue, boolean isDone) throws BennException {
        super(description);
        try {
            System.out.println("in deadline ctor" + datetimeDue);
            this.datetimeDue = parseDateTime(datetimeDue);
        } catch (DateTimeParseException exception) {
            throw new BennException("Invalid date received!");
        }
        this.isDone = isDone;
    }

    /**
     * Parses the given date/time string into a {@link LocalDateTime}.
     *
     * <p>The string must be in the format {@code dd/MM/yyyy HH:mm}, i.e.,
     * two parts separated by whitespace: a date and a time.</p>
     *
     * @param datetimeDue the date/time string to parse
     * @return the parsed {@link LocalDateTime}
     * @throws BennException if the input is null, malformed, or not exactly two parts
     */
    private static LocalDateTime parseDateTime(String datetimeDue) throws BennException {
        String[] parts = datetimeDue == null ? null : datetimeDue.trim().split("\\s+");
        if (parts == null || parts.length != 2) {
            throw new BennException("Datetime must have exactly [date, time].");
        }
        String dateTimeString = parts[0] + "T" + parts[1];
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Returns a string representation of this deadline task, suitable for display to the user.
     *
     * @return a formatted string of the form {@code [D][ ] description (by: yyyy-MM-ddTHH:mm)}
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.datetimeDue.format(formatter));
    }

    /**
     * Returns the storage representation of this deadline task, suitable for writing to disk.
     *
     * @return a pipe-delimited string in the format:
     *         {@code D | <done> | <description> | <by>}
     */
    @Override
    public String toStorageFormat() {
        return String.format("D | %d | %s | %s", this.getIsDone() ? 1 : 0, this.getDescription(), this.datetimeDue.format(formatter));
    }
}
