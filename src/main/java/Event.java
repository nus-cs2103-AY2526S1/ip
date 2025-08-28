public class Event extends Task {
    private final String from, to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public Event(String description, boolean isDone, String fromTo) {
        super(description, isDone);
        String[] parts = fromTo.split(" ", 2);
        if (parts.length == 2) {
            this.from = parts[0];
            this.to = parts[1];
        } else {
            this.from = "";
            this.to = "";
        }
    }

    @Override 
    protected String kind() {
        return "[E]"; 
    }

    @Override
    public String toString() {
        return kind() + status() + " " + super.description + " (from: " + from + " to: " + to + ")";
    }
    @Override
    public String toSaveFormat() {
        return String.format("E | %d | %s | %s", isDone() ? 1 : 0, description, from + " " + to);
    }
}