package ip;

/**
 * Represents a task that occurs within a specific time frame.
 * An Event has a description, a start time, and an end time.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs a new Event task.
     *
     * @param description the description of the event
     * @param from the start time or date of the event
     * @param to the end time or date of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time or date of the event.
     *
     * @return the start time or date as a String
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time or date of the event.
     *
     * @return the end time or date as a String
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns the type icon of this task.
     *
     * @return "E" for Event
     */
    @Override
    public String getTypeIcon() {
        return "E";
    }

    /**
     * Returns a string representation of the Event task, including
     * its type, status, description, start time, and end time.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] " + description
                + " (from: " + from + " to: " + to + ")";
    }
}
