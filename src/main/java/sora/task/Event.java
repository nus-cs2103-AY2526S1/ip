package sora.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(TaskType.EVENT, description);
        this.from = from;
        this.to = to;
    }

    public String fromToFormat() {
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH));
    }

    public String toToFormat() {
        return to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH));
    }

    @Override
    public String toString() {
        return super.toString() + " (from: "
                + this.fromToFormat() + " to: " + this.toToFormat() + ")";
    }
}
