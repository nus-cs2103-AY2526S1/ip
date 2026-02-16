package botchat.task;

import botchat.util.DateTime;

import java.time.LocalDateTime;

/**
 * Represents a task that has a from and to date and time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a tasks that has a from and to date and time.
     * @param description the String description of a task.
     * @param from the starting date and time of a task, in yyyy-MM-dd HH:mm
     * @param to the ending date and time of a task, in yyyy-MM-dd HH:mm.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTime.INPUT_DATETIME);
        this.to = LocalDateTime.parse(to, DateTime.INPUT_DATETIME);
    }

    @Override
    public String toStorage() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(DateTime.INPUT_DATETIME)
                + " | " + to.format(DateTime.INPUT_DATETIME);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTime.OUTPUT_DATETIME)
                + " to: " + to.format(DateTime.OUTPUT_DATETIME) + ")";
    }
}
