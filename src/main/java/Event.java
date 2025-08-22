public class Event extends Task {
    String to;
    String from;

    // Events
    Event(String name, Boolean completed, String from, String to) {
        super(name, completed);
        this.to = to;
        this.from = from;
    }

    public String print() {
        return "[E] [" + (this.completed ? "X" : " ") + "] " + this.name +
                " (from: " + this.from + " to: " + this.to + ")";
    }
}
