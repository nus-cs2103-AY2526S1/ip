package dyke.tasks;

import dyke.parse.DateTimeParser;
import dyke.parse.DykeException;

/**
 * Houses methods for {@code Event} task.
 */
public class Event extends Task {
    private DateTimeParser from;
    private DateTimeParser to;

    /**
     * Creates an {@code Event} task.
     * @param desc Description of event.
     * @param from Start datetime
     * @param to End datetime
     * @throws DykeException If any inputs are unrecognized.
     */
    public Event(String desc, String from, String to) throws DykeException {
        super(desc);
        this.from = DateTimeParser.parse(from);
        this.to = DateTimeParser.parse(to);
        if (this.from.timeDifference(this.to) < 0) {
            throw new DykeException("Dude, are we travelling to the past??");
        }
        if (!this.to.isConsistentFormat(this.from)) {
            throw new DykeException("Hey, keep the datetime consistent... please?");
        }
    }

    public DateTimeParser getFrom() {
        return from;
    }

    public DateTimeParser getTo() {
        return to;
    }

    public DateTimeParser[] getTime() {
        return new DateTimeParser[]{from, to};
    }

    @Override
    public String encode() {
        return "E | " + super.encode() + " | "
                + this.from.reString() + " | " + this.to.reString();
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.from.reString()
                + " to: " + this.to.reString() + ")";
    }
}
