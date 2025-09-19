package jimmy.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jimmy.exception.JimmyException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for an Event object.
     *
     * @param description The description of the task.
     * @param start       The start time of the task.
     * @param end         The end time of the task.
     */
    public Event(String description, boolean completed, String tag, String start, String end) throws JimmyException {
        super(description, completed, tag);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        // Parse start date and time
        try {
            LocalDateTime d1 = LocalDateTime.parse(start, dateTimeFormatter);
            this.start = d1;
        } catch (DateTimeParseException e) {
            this.start = handleMissingStart(start);
        }

        // Parse end date and time
        try {
            LocalDateTime d1 = LocalDateTime.parse(end, dateTimeFormatter);
            this.end = d1;
        } catch (DateTimeParseException e) {
            this.end = handleMissingEnd(end);
        }
    }

    /**
     * Returns a LocalDateTime with a time that is the start of the day 00:00.
     * @param start String with the provided date.
     * @return LocalDateTime with a time that is the start of the day 00:00.
     * @throws JimmyException If the date or time is invalid.
     */
    public LocalDateTime handleMissingStart(String start) throws JimmyException {
        try {
            // If missing time, set it to 00:00 as default
            LocalDateTime d1 = LocalDate.parse(start).atStartOfDay();
            return d1;
        } catch (DateTimeParseException e2) {
            throw new JimmyException("Please add a valid date and time!");
        }
    }

    /**
     * Returns a LocalDateTime with a time that is the start of the day 00:00.
     * @param end String with the provided date.
     * @return LocalDateTime with a time that is the start of the day 00:00.
     * @throws JimmyException If the date or time is invalid.
     */
    public LocalDateTime handleMissingEnd(String end) throws JimmyException {
        try {
            // If missing time, set it to 00:00 as default
            LocalDateTime d1 = LocalDate.parse(end).atStartOfDay();
            if (this.start.isAfter(d1)) {
                throw new JimmyException("Error: Start date is after end date!");
            }
            return d1;
        } catch (DateTimeParseException e2) {
            throw new JimmyException("Please add a valid end date and time!");
        }
    }


    /**
     * Returns the formatted string to be used for storing in the hard disk.
     *
     * @return Formatted string.
     */
    public String toStorageString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return String.format("%s|EVENT|%s|%s|%s|%s", super.toStorageString(), this.getDescription(),
                this.getCompleted(), this.start.format(dateTimeFormatter), this.end.format(dateTimeFormatter));
    }

    /**
     * Returns the string representation of this task.
     *
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        String type = "E";
        return String.format("[%s]%s (from: %s to: %s)", type, super.toString(),
                this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")),
                this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }
}

