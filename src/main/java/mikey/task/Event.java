package mikey.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h.mma");

    /**
     * Initializes an Event instance
     * @param description Description of task
     * @param start start of event in "D/M/YYYY HHMM" format
     * @param end end of event in "D/M/YYYY HHMM" format
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSaveString() {
        String result = "E | " + (isDone ? "1" : "0") + " | " + description + " | " + this.start.format(FORMATTER)
                + " | " + this.end.format(FORMATTER);
        if (isTagged()) {
            return result + " | " + tag;
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "[E]" + super.toString() + "\n      (from: " + start.format(FORMATTER) + " to: "
                + end.format(FORMATTER) + ")";
        return result;
    }
}
