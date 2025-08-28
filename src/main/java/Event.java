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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
