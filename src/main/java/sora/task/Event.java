package sora.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an {@code Event} task that occurs within a specific time range.
 * It includes a start time and an end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an {@code Event} task with the specified description,
     * start time, and end time.
     *
     * @param description the description of the event.
     * @param from the start time of the event.
     * @param to the end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(TaskType.EVENT, description);
        this.from = from;
        this.to = to;
    }

    /**
     * Formats the start time into a different string.
     *
     * @return the formatted start time in the pattern "MMM dd yyyy HHmm".
     */
    public String fromToFormat() {
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH));
    }

    /**
     * Formats the end time into a different string.
     *
     * @return the formatted start time in the pattern "MMM dd yyyy HHmm"
     */
    public String toToFormat() {
        return to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH));
    }

    /**
     * Returns the string representation of the {@code Event} task,
     * including its description, status, start time and end time.
     *
     * @return the formatted event task string.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: "
                + this.fromToFormat() + " to: " + this.toToFormat() + ")";
    }
}
