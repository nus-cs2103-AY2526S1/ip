package chathyt.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task in the Duke application.
 * Each event has a description, a 'from' indicating
 * its starting time, a 'to' indicating its end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    private static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    public Event(String description, String fromString, String toString) {
        super(description);
        this.from = LocalDateTime.parse(fromString, inputFormat);
        this.to = LocalDateTime.parse(toString, inputFormat);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(outputFormat) + " to: " + to.format(outputFormat) + ")";
    }
}
