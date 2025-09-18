package tasks;

import java.time.LocalDateTime;

import exception.RainyException;
import util.DateTimeUtil;

/**
 * Represents a task that occurs during a time interval (from start to end).
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event task with a description and time range provided as strings.
     *
     * @param description the task description
     * @param from the start time as a string
     * @param to the end time as a string
     * @throws RainyException if the date/time format is invalid
     */
    public Event(String description, String from, String to) throws RainyException {
        super(description, TaskType.EVENT);
        this.from = DateTimeUtil.parse(from);
        this.to = DateTimeUtil.parse(to);
    }

    /**
     * Constructs an Event task with a description and time range.
     *
     * @param description the task description
     * @param from the start time
     * @param to the end time
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
        assert this.from != null && this.to != null : "Event times must not be null";
        assert !this.from.isAfter(this.to) : "from cannot be after to";
    }

    @Override
    public String toString() {
        return super.toString()
                + " (from: " + DateTimeUtil.formatForDisplay(from)
                + " to: " + DateTimeUtil.formatForDisplay(to) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
