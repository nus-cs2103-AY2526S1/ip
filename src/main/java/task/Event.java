package task;

import java.time.LocalDate;

/**
 * Event class representing event subclass of Task
 */
public class Event extends Task {
    /**
     * Represents when the event starts
     */
    private final LocalDate from;
    /**
     * Represents when the event ends
     */
    private final LocalDate to;

    /**
     * Constructor of event class
     * @param description Description of event
     * @param from When the event starts
     * @param to When the event ends
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Task.dateToString(this.from)
                + " to: " + Task.dateToString(this.to) + ")";
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + this.from + " | " + this.to;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event t) {
            return super.equals(o) && t.from.equals(this.from) && t.to.equals(this.to);
        }
        return false;
    }
}
