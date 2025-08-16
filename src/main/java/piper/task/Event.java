package piper.task;

public class Event extends Task {
    protected final String taskType = "E";
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }

}