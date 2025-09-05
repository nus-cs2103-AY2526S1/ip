package dobby.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, String fromStr, String untilStr) {
        super(description, TaskType.EVENT);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.start = LocalDateTime.parse(fromStr, inputFormatter);
        this.end = LocalDateTime.parse(untilStr, inputFormatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[E] " + super.toString() +
                " (from: " + start.format(outputFormatter) +
                " to: " + end.format(outputFormatter) + ")";
    }
}

