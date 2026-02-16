package alice.task;

import alice.Alice;
import alice.Task;
import alice.exceptions.AliceException;

import java.time.LocalDateTime;

public class Event extends DateTask {

    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String description, String start, String end) throws AliceException {
        super(description);
        this.start = parseDate(start);
        this.end = parseDate(end);
    }

    public Event(String description, boolean isDone, String start, String end) throws AliceException {
        super(description, isDone);
        this.start = parseDate(start);
        this.end = parseDate(end);
    }

    public void setStart(String start) throws AliceException {
        this.start = parseDate(start);
    }

    public void setEnd(String end) throws AliceException {
        this.end = parseDate(end);
    }

    @Override
    public String toFileFormat() {
        return "E | " + getStatusIcon() + " | " + description + " | " +
                start.format(INPUT_FORMAT) + " | " + end.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description +
                " (from: " + start.format(OUTPUT_FORMAT) + " to: " + end.format(OUTPUT_FORMAT) + ")";
    }
}
