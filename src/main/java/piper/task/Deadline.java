package piper.task;

public class Deadline extends Task {
    protected final String taskType = "D";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSerializedLine() {
        String doneField = getStatusIcon().equals("X") ? "1" : "0";
        return "D | " + doneField + " | " + this.getDescription() + " | " + this.by;
    }

}