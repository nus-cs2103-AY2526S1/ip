package sam.task;

public class Event extends Task {
    private final String from, to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description);
        if (isDone) this.markDone();
        this.from = from;
        this.to = to;
    }

    @Override 
    protected String kind() {
        return "[E]"; 
    }

    @Override
    public String toString() {
        return kind() + status() + " " + description + " (from: " + from + " to: " + to + ")";
    }

    public String getFromRaw() {
        return from;
    }
    
    public String getToRaw() {
        return to;
    }
}
