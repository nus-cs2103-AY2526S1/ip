package piper.task;

public class Deadline extends Task {
    protected final String taskType = "D";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString();
    }

}