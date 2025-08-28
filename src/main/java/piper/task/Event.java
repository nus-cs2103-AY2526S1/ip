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

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSerializedLine() {
        String doneField = getStatusIcon().equals("X") ? "1" : "0";
        return "E | " + doneField + " | " + this.getDescription() + " | " + this.from + " | " + this.to;
    }

}