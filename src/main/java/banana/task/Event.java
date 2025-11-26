package banana.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description, start date/time, and end date/time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task.
     *
     * @param description Description of the event task.
     * @param from        Start date/time in the format "yyyy-MM-dd HHmm".
     * @param to          End date/time in the format "yyyy-MM-dd HHmm".
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Gets the start date/time of the event task.
     * @return The start date/time as a LocalDateTime object.
     */
    public LocalDateTime getFrom() {
        return from;
    }
    /**
     * Gets the end date/time of the event task.
     * @return The end date/time as a LocalDateTime object.
     */
    public LocalDateTime getTo() {
        return to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"))
                + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";
    }
}
