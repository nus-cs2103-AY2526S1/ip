package piper.task;

/**
 * A task without additional fields.
 */
public class Todo extends Task {
    protected final String taskType = "T";

    /**
     * Creates a Todo.
     *
     * @param description task description.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString();
    }

    @Override
    public String toSerializedLine() {
        return super.toSerializedLine();
    }

}
