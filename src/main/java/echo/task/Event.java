package echo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task. An <code>Event</code> object is a subtype of <code>Task</code>
 * that stores LocalDateTime <code>from</code> and LocalDateTime <code>to</code> which indicates
 * the start and end of the task.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toDataString() {
        DateTimeFormatter storageFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return "E | " + super.toDataString()
                + " | " + this.from.format(storageFormatter)
                + " | " + this.to.format(storageFormatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }
}
