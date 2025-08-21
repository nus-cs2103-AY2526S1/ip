public class Event extends Task {
    private final String from, to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override 
    protected String kind() {
        return "[E]"; 
    }

    @Override
    public String toString() {
        return kind() + status() + " " + super.description + " (from: " + from + " to: " + to + ")";
    }
}