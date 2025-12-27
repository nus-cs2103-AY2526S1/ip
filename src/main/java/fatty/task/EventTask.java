package fatty.task;

import java.time.LocalDateTime;

/**
 * Event Task includes description, from and to time.
 */
public class EventTask extends Task {
    private static final String type = "[E]";
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructor for Event Task
     * @param description name
     * @param from from date and time
     * @param to to date and time
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;

    }

    @Override
    public String toString() {
        return type + super.toString() + " (from: " + this.from.format(DISPLAY_FORMAT)
                + " to: " + this.to.format(DISPLAY_FORMAT) + ")";
    }

    /**
     * Reformat Task into "type | status | description | times (d/M/yyyy HHmm)" to save into local file.
     * @return string to be saved
     */
    @Override
    public String toDataString() {
        return "E | " + (this.isMark ? "1" : "0") + " | " + this.description + " | "
                + this.from.format(SAVE_FORMAT) + " | " + this.to.format(SAVE_FORMAT);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }
}

