package dobby.task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task implements Serializable {
    private static final long serialVersionUID = 1L;
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert start != null : "Event date 'from' cannot be null";
        assert end != null : "Event date 'to' cannot be null";
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[E] " + super.toString() +
                " (from: " + start.format(outputFormatter) +
                " to: " + end.format(outputFormatter) + ")";
    }
}
