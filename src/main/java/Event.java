public class Event extends Task{
    private final String start;
    private final String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public Event(String name, String start, String end, boolean isDone) {
        this(name, start, end);
        setDone(isDone);
    }

    @Override
    public String taskToString() {
        return String.format("%s | %b | %s | %s | %s", "E", getDone(), getName(), this.start, this.end);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", "E", super.toString(), this.start, this.end);
    }
}

