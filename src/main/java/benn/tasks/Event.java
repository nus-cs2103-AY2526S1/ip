package benn.tasks;

import benn.exceptions.BennException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a start and end time in Benn the Chatbot.
 *
 * <p>An {@code Event} has a description, a start date/time, an end date/time,
 * and a completion status. Both start and end are stored internally as
 * {@link LocalDateTime} objects and must be provided in the format
 * {@code dd/MM/yyyy HH:mm}. For example: {@code 12/08/2002 12:23}.</p>
 *
 * <p>When serialized to storage, the event uses the format
 * {@code dd/MM/yyyy'T'HH:mm} to ensure unambiguous parsing.</p>
 */
public class Event extends Task{
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm");
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Constructs a new {@code Event} task with the specified description,
     * start date/time, end date/time, and completion status.
     *
     * @param description the description of the event
     * @param startDateTimeString the start date/time string in {@code dd/MM/yyyy HH:mm} format
     * @param endDateTimeString the end date/time string in {@code dd/MM/yyyy HH:mm} format
     * @param isDone whether the task is marked as completed
     * @throws BennException if either date/time string is invalid or not in the expected format
     */
    public Event(String description, String startDateTimeString, String endDateTimeString, boolean isDone) throws BennException {
        super(description);
        try {
            this.startDateTime = parseDateTime(startDateTimeString.split(" "));
            this.endDateTime = parseDateTime(endDateTimeString.split(" "));
        } catch (DateTimeParseException exception) {
            throw new BennException("Invalid date received!");
        }
        if (startDateTime.isAfter(endDateTime)) {
            throw new BennException("Start time cannot be after end time.");
        }
        this.isDone = isDone;
    }

    /**
     * Parses the given date/time string parts into a {@link LocalDateTime}.
     *
     * <p>The array must contain exactly two parts: a date and a time.
     * For example: {@code ["12/08/2002", "12:23"]}.</p>
     *
     * @param datetimeParts an array containing [date, time]
     * @return the parsed {@link LocalDateTime}
     * @throws BennException if the array is null or does not contain exactly two parts
     */
    private static LocalDateTime parseDateTime(String[] datetimeParts) throws BennException {
        if (datetimeParts == null || datetimeParts.length != 2) {
            throw new BennException("Datetime must have exactly [date, time].");
        }
        String dateTimeString = datetimeParts[0] + "T" + datetimeParts[1];
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Returns a string representation of this event task, suitable for display to the user.
     *
     * @return a formatted string of the form {@code [E][ ] description (from: start to: end)}
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.startDateTime.format(formatter), this.endDateTime.format(formatter));
    }

    /**
     * Returns the storage representation of this event task, suitable for writing to disk.
     *
     * @return a pipe-delimited string in the format:
     *         {@code E | <done> | <description> | <from> | <to>}
     */
    @Override
    public String toStorageFormat() {
        return String.format("E | %d | %s | %s | %s", this.getIsDone() ? 1 : 0, this.getDescription(), this.startDateTime.format(formatter), this.endDateTime.format(formatter));
    }

}
