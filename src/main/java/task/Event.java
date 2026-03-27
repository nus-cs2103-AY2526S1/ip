package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import util.ShrekException;

/**
 * Represents an event task with specific start and end times.
 * Extends the base Task class to include time-bound event functionality.
 */
public class Event extends Task {
    // input format we accept
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // how we want to display it back to the user
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy, h:mma").withLocale(java.util.Locale.ENGLISH);

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description the event description
     * @param from        the start time string in yyyy-MM-dd HH:mm format
     * @param to          the end time string in yyyy-MM-dd HH:mm format
     * @throws ShrekException if the date/time format is invalid
     */
    public Event(String description, String from, String to) throws ShrekException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ShrekException(
                    "Shrek needs a valid date/time in *yyyy-MM-dd HH:mm* format, e.g. 2025-01-01 05:00"
            );
        }
        // Ensure start time is not after end time
        if (this.from.isAfter(this.to)) {
            throw new ShrekException("Shrek says the start time must be before or equal to the end time!\n"
                    + "Start: " + this.from.format(OUTPUT_FORMAT) + "\n"
                    + "End: " + this.to.format(OUTPUT_FORMAT));
        }
    }

    /**
     * Returns the start time of the event.
     *
     * @return the LocalDateTime representing the event start time
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the LocalDateTime representing the event end time
     */
    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof Event)) {
            return false;
        }

        Event other = (Event) obj;
        return this.from.equals(other.from) && this.to.equals(other.to);
    }

    /**
     * Returns a string representation of the Event task.
     * Includes task type, status, description, and formatted start/end times.
     *
     * @return formatted string representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(OUTPUT_FORMAT)
                + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the file format representation of the Event task for storage.
     * Format: "E | status | description | start_time | end_time"
     *
     * @return string representation suitable for file storage
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + from.format(INPUT_FORMAT)
                + " | " + to.format(INPUT_FORMAT);
    }
}
