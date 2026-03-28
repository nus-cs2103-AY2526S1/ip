package chlo.task;

import java.time.LocalDateTime;

import chlo.exception.ChloException;
import chlo.ui.Parser;

/**
 * Creates an event task
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Event constructor
     * @param description
     * @param from
     * @param to
     * @throws ChloException
     */
    public Event(String description, String from, String to) throws ChloException {
        super(description);
        this.from = Parser.parseDate(from);
        this.to = Parser.parseDate(to);
        this.raw = String.format("event %s /from %s /to %s", description, from, to);

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.getFormattedDate(this.from) + " to: " + Parser.getFormattedDate(this.to) + ")";
    }

    /**
     * To get from time for sort command to handle
     * @return
     */
    public LocalDateTime getFrom() {
        return from;
    }
}
