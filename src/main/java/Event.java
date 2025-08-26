public class Event extends Task{
    private final DateOrTime start;
    private final DateOrTime end;

    public Event(String name, String start, String end) throws ZellException {
        super(name);
        this.start = new DateOrTime(start);
        this.end = new DateOrTime(end);
    }

    public Event(String name, String start, String end, boolean isDone) throws ZellException {
        this(name, start, end);
        setDone(isDone);
    }

    @Override
    public String taskToString() {
        return String.format("%s | %b | %s | %s | %s", "E", getDone(), getName(),
                this.start.originalFormat(), this.end.originalFormat());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", "E", super.toString(),
                this.start.toString(), this.end.toString());
    }
}

