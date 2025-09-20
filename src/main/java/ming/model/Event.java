package ming.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents an event task with a description, start date & time, and end date & time.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event task.
     *
     * @param description Description of the event task.
     * @param from        Start date and time of the event task.
     * @param to          End date and time of the event task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, List<String> tags) {
        super(description, tags);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toDataString() {
        return "E | " + super.toDataString() + " | "
                + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | "
                + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}
