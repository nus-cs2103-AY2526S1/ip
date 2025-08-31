package stella;

/**
 * Represents a task that have a start date/time and an end date/time. An Event is
 * represented by 3 strings, which are the description, the start and the end.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " | to: " + this.end + ")";
    }
}

