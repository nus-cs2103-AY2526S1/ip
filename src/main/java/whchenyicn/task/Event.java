package whchenyicn.task;

import java.time.LocalDate;

/**
 * An event task with a start time and end time.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Creates an Event Task.
     *
     * @param description Description of event.
     * @param from Start date.
     * @param to End date.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSave() {
        return String.format("E | %d | %s | %s | %s", getDoneFlag(), getDesc(), from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

}
