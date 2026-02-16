package weewee.task;

/** Represents a Task with name and done status. */
public class Task {
    private boolean isDone;
    private final String taskName;

    /**
     * Constructs a Task with the given details.
     *
     * @param taskName Name/description of the task.
     */
    public Task(String taskName) {
        this.isDone = false;
        this.taskName = taskName;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getIsdone() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        return getIsdone() + " " + taskName;
    }
}
