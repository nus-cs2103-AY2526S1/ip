package luna.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a {@code Task} that has a start and end
 */
public class Event extends Task {
    private final LocalDate start;
    private final LocalDate end;

    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTaskType() {
        return "event";
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                start.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                end.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}