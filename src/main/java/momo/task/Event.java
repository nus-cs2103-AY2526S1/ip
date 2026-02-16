package momo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date/time.
 * Extends {@link Task} and adds {@code from} and {@code to} fields to indicate
 * the duration of the event.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates a new {@code Event} task with the given description and start/end times.
     *
     * @param description the description of the event.
     * @param from the start date and time of the event.
     * @param to the end date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates a new {@code Event} task with the given description, start/end times,
     * and completion status.
     *
     * @param description the description of the event.
     * @param from the start date and time of the event.
     * @param to the end date and time of the event.
     * @param isDone whether the event is marked as done.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date and time of the event.
     *
     * @return the {@link LocalDateTime} representing the start of the event.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end date and time of the event.
     *
     * @return the {@link LocalDateTime} representing the end of the event.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns a string representation of the event task,
     * including its type, description, completion status, and start/end times.
     *
     * @return a formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }

    /**
     * Converts the event task to a string format suitable for saving to disk.
     *
     * @return a string in the format {@code "E | <status> | <description> | <yyyy-MM-dd HHmm> | <yyyy-MM-dd HHmm>"}.
     */
    @Override
    public String convertToSaveFormat() {
        return "E | " + super.convertToSaveFormat()
                + String.format(" | %s | %s",
                from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }
}
