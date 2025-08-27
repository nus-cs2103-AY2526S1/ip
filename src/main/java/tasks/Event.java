package tasks;

import exception.RainyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) throws RainyException {
        super(description, TaskType.EVENT);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    private LocalDateTime parseDateTime(String input) throws RainyException {
        DateTimeFormatter[] formatters = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
        };

        for (DateTimeFormatter f : formatters) {
            try {
                return LocalDateTime.parse(input.trim(), f);
            } catch (Exception ignored) {}
        }
        throw new RainyException("oh no!!! wrong date format... please use yyyy-MM-dd HHmm or d/M/yyyy HHmm.");
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
        return super.toString() + " (from: " + from.format(displayFormat)
                + " to: " + to.format(displayFormat) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
