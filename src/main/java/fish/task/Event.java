package fish.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an event that occurs within a specified time range.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an event task with the provided description and time range.
     *
     * @param description description of the event
     * @param from        start time in {@code yyyy-MM-dd HHmm} format
     * @param to          end time in {@code yyyy-MM-dd HHmm} format
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        if (this.from.isAfter(this.to)) {
            throw new IllegalArgumentException("Event 'from' time must be earlier than or equal to 'to' time.");
        }
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ", to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return String.join(" | ", "E", isDone ? "1" : "0", description,
                this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event other = (Event) obj;
        return from.equals(other.from) && to.equals(other.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), from, to);
    }
}
