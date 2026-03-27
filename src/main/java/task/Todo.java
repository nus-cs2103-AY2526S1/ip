package task;

/**
 * A simple task without deadlines or durations.
 */
public class Todo extends Task {
    private boolean isDone;

    public Todo(String taskName, boolean done) {
        super(taskName, done);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
