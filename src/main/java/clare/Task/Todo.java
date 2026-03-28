package clare.task;

/**
 * Represents a task type which do not have any time constraints
 */
public class Todo extends Task {
    public Todo(String title, boolean isDone) {
        super(title.trim(), isDone);
    }

    @Override
    String getTypeString() {
        return "T";
    }

    @Override
    public TaskType getType() {
        return TaskType.T;
    }

    @Override
    public String toString() {
        return "[" + getTypeString() + "]" + getIsDoneStatus() + " " + getTitle();
    }

    @Override
    public String toSaveString() {
        return getTypeString() + "|" + getIsDoneInt() + "|" + getTitle();
    }
}
