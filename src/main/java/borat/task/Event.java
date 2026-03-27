package borat.task;

/**
 * An event spanning a start and end date/time.
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Creates an event with description and time range.
     *
     * @param description task description
     * @param start formatted start time
     * @param end formatted end time
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + start + " | " + end;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }
}