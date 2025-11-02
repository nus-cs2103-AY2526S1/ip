package duke;

/**
 * Represents an event task with a specific start and end time.
 * Inherits from Task class and adds event timing information.
 */
public class Event extends Task {
    /** The start time of the event */
    private String from;

    /** The end time of the event */
    private String to;

    /**
     * Creates a new Event task with the given description, start time, and end time.
     *
     * @param description The description of the event task
     * @param from The start time of the event
     * @param to The end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start time of the event.
     * @return The start time
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the end time of the event.
     * @return The end time
     */
    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
