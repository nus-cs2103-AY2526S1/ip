package piper.task;

public class Todo extends Task {
    protected final String taskType = "T";

    public Todo(String description) {
        super(description);
    }

    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString();
    }

}