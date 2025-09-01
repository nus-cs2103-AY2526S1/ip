public class Event extends Task {
    String to;
    String from;

    // Events
    Event(String name, Boolean completed, String from, String to) {
        super(name, completed);
        this.to = to;
        this.from = from;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String print() {
        return "[E] [" + (this.completed ? "X" : " ") + "] " + this.name +
                " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsCsv() {
        return "E," + this.completed.toString() + "," + this.name + "," + this.from + "," + this.to;
    }
}
