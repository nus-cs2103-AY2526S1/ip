// Deadline Task

public class Event extends Task {

    private String from;
    private String to;

    public Event(String description) {
        super(description.split("\\/from ", 2)[0]);

        String[] parts = description.split("\\/from ", 2);
        parts = parts[1].split("\\ /to ", 2);

        this.from = parts[0];
        this.to = parts[1];
    }

    // Used for deserialization
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String serialize() {
        return "E" + "|" + (this.isDone() ? "1" : "0") + "|" + this.getDescription() + "|" + this.from + "|" + this.to;
    }
}
