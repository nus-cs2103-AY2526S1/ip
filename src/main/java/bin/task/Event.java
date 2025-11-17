package bin.task;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event type of task.
     */
    public Event(Boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = super.readDate(from);
        this.to = super.readDate(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(super.stringFormat)
                + " to: " + to.format(super.stringFormat) + ")";
    }

    @Override
    public String addTask() {
        int num = isDone ? 1 : 0;
        return "E | " + num + " | " + description + " | "
                + from.format(super.format) + " | " + to.format(super.format);
    }
}
