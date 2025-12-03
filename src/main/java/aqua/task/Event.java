package aqua.task;

import java.time.temporal.Temporal;

import aqua.command.parser.PriorityParser;
import aqua.exception.InvalidArgumentException;
import aqua.time.Date;

/**
 * Represents an Event task with a description, start time, and end time.
 */
public class Event extends Task {
    private Temporal from;
    private Temporal to;

    /**
     * Creates an Event task.
     *
     * @param description description of the task
     * @param from Start time of the event
     * @param to End time of the event
     * @throws InvalidArgumentException If the description, start time or end time is empty
     */
    public Event(String description, String from, String to) throws InvalidArgumentException {
        super(description);

        if (from.isBlank()) {
            throw new InvalidArgumentException("Start date (/from) cannot be empty");
        }

        if (to.isBlank()) {
            throw new InvalidArgumentException("End date (/to) cannot be empty");
        }

        this.from = Date.parse(from);
        this.to = Date.parse(to);
    }

    /**
     * Creates an Event task with priority.
     *
     * @param description description of the task
     * @param from Start time of the event
     * @param to End time of the event
     * @param priority Priority of the task
     * @throws InvalidArgumentException If the description, start time, end time or priority is empty or invalid
     */
    public Event(String description, String from, String to, String priority) throws InvalidArgumentException {
        this(description, from, to);
        this.priority = PriorityParser.parse(priority);
    }

    @Override
    public String toString() {
        String fromString;
        String toString;
        try {
            fromString = Date.toDateString(from);
            toString = Date.toDateString(to);
        } catch (InvalidArgumentException e) {
            fromString = (from != null ? from.toString() : "invalid");
            toString = (to != null ? to.toString() : "invalid");
        }

        return String.format("[E]%s (from: %s, to: %s)", super.toString(), fromString, toString);
    }

    @Override
    public String toStorageString() {
        int priorityOrdinal = this.priority == null ? -1 : this.priority.ordinal();
        return String.format("E | %d | %d | %s ^ %s ^ %s",
                this.isDone ? 1 : 0, priorityOrdinal, this.description, this.from, this.to);
    }
}
