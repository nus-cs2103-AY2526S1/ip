public class Event extends Task{
    protected String from;
    protected String until;

    public Event(String description, String from, String until) {
        super(description);
        this.from = from;
        this.until = until;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + " to: " + this.until + ")";
    }
}
