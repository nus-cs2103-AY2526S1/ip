package hermione.tasks;

import java.time.LocalDateTime;

import hermione.utils.DateUtils;

/**
 * Represents an Event task in the Hermione application.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event with the specified description,
     * completion status, start date and time, and end date and time.
     *
     * @param description The description of the event.
     * @param isCompleted Whether the event is completed.
     * @param from        The start date and time of the event.
     * @param to          The end date and time of the event.
     */
    public Event(String description, boolean isCompleted, LocalDateTime from, LocalDateTime to) {
        super(description, isCompleted);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start date and time of the event.
     *
     * @return The start date and time.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Gets the end date and time of the event.
     *
     * @return The end date and time.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[" + TaskType.EVENT.getCode() + "]"
                + super.toString()
                + " (from: %s to: %s)".formatted(DateUtils.formatDate(this.from), DateUtils.formatDate(this.to));
    }
}
