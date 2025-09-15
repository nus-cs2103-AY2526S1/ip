package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class managing the internals of an Event object
 */
@SuppressWarnings("checkstyle:Regexp")
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructs an Event object
     * @param description task/event name
     * @param start start date of event
     * @param end end date of event
     */
    public Event(String description, String start, String end) {
        super(TaskType.EVENT, description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    public LocalDate getStart() {
        return this.start;
    }

    public LocalDate getEnd() {
        return this.end;
    }

    public void setStart(String newStart) {
        this.start = LocalDate.parse(newStart);
    }

    public void setEnd(String newEnd) {
        this.end = LocalDate.parse(newEnd);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start.toString() + " | " + end.toString();
    }
}
