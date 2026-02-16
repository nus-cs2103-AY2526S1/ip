package meo.task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String text, String from, String to) {
        super(text, null);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return "[E][" + mark + "] " + description + " | from: " + from + " to: " + to;
    }
}
