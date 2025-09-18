package joobot.task;

import joobot.main.DateTimeValue;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    private final DateTimeValue from;
    private final DateTimeValue to;

    /**
     * Constructs a new {@code Event} task with a description and time range.
     *
     * @param description The description of the event.
     * @param from        The starting time of the event (string format).
     * @param to          The ending time of the event (string format).
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = new DateTimeValue(from);
        this.to = new DateTimeValue(to);
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    /**
     * Returns a string representation of this event task for display.
     *
     * @return The formatted event task string.
     */
    @Override
    public String toString() {
        return getTypeIcon() + super.toString()
                + " (from: " + from.toDisplayString()
                + " to: " + to.toDisplayString() + ")";
    }

    /**
     * Returns the file format string for this event task.
     *
     * @return The string in file storage format: "E | status | description | from | to".
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone() ? "1" : "0")
                + " | " + getDescription()
                + " | " + from.toFileString()
                + " | " + to.toFileString();
    }
}
