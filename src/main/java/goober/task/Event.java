package goober.task;

import java.time.LocalDateTime;

import goober.helper.Parser;

/**
 * Represents a task scheduled over a start and end date/time.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs a new Event.
     *
     * @param description the description
     * @param from        the from
     * @param to          the to
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.dateTimeToString(from) + " to: "
                + Parser.dateTimeToString(to) + ")";
    }
}
