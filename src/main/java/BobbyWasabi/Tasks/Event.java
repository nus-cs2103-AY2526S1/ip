package BobbyWasabi.Tasks;

public class Event extends BobbyWasabi.Tasks.Task {
    private String start;
    private String end;

    public Event(String description, boolean isMarked, String start, String end) {
        super(description, isMarked);
        this.start = start;
        this.end = end;
    }

    public String getDuration() {
        return String.format("(from:%sto:%s)",
                this.start,
                this.end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + this.getDuration();
    }

    @Override
    public String getData() {
        return String.format("E|%s|%s|%s|%s",
                super.getDescription(), super.checked(), this.start, this.end);
    }
}
