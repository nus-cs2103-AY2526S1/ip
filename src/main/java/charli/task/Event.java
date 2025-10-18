package charli.task;

/**
 * Represents an event task with specific start and end times.
 * Events are time-bound tasks that occur during a particular time period.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new Event task with description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        assert from != null && !from.isEmpty()
                && to != null && !to.isEmpty()
                : "from and to should not be null";
        this.from = from;
        this.to = to;
    }


    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }


}
