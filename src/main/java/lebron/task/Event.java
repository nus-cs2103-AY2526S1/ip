package lebron.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lebron.common.Constants;

/**
 * Represents a task that is an event occurring over a period of time.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructor for Event class
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        assert start != null : "Event start date should not be null";
        assert end != null : "Event end date should not be null";
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return this.start;
    }

    public LocalDate getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return Constants.TYPE_PREFIX_E + super.toString()
                + " (from: " + start.format(DateTimeFormatter.ofPattern(Constants.DISPLAY_DATE_PATTERN))
                + " to: " + end.format(DateTimeFormatter.ofPattern(Constants.DISPLAY_DATE_PATTERN)) + ")";
    }
}
