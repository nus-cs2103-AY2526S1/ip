public class Event extends Task {
    public Event(String description) {
        super(description);
    }

    @Override
    public String getStatus() {
        return "[E]";
    }
}