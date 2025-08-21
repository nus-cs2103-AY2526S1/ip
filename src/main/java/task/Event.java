package task;

public class Event extends Task {
    public Event(String description) {
        super(description);
    }

    @Override
    public void complete() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
