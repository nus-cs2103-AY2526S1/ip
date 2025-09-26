package winnie.task;

import java.time.LocalDateTime;
import winnie.util.DateTimeUtil;
import winnie.exception.InvalidDateTimeException;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates an event task.
     *
     * @param description The description of the task.
     * @param from        The start date and time.
     * @param to          The end date and time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskEnum.EVENT);
        assert from != null && to != null : "Event start and end times cannot be null";
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an event task.
     *
     * @param description The description of the task.
     * @param fromString  The start date and time as a string.
     * @param tostring    The end date and time as a string.
     * @throws InvalidDateTimeException If the date and time strings are invalid.
     */
    public Event(String description, String fromString, String tostring) throws InvalidDateTimeException {
        super(description, TaskEnum.EVENT);
        this.from = DateTimeUtil.parseDateTime(fromString);
        this.to = DateTimeUtil.parseDateTime(tostring);
    }

    /**
     * Gets the start date and time.
     *
     * @return The start date and time.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Gets the end date and time.
     *
     * @return The end date and time.
     */
    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + getDescription() + " (from: " +
                DateTimeUtil.formatForDisplay(from) + " to: " + DateTimeUtil.formatForDisplay(to) + ")";
    }
}
