package buddy.model;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        assert !description.isBlank() : "Event description cannot be blank";
        this.from = from;
        this.to = to;
        assert !from.isBlank() && !to.isBlank() : "Event range cannot be blank";
    }

    @Override
    public String getType() {
        return "E";
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
