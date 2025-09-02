package paul.task;

/**
 * A task for Paul.
 * Stores the task's description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task with the given description.
     * The task will be marked not done by default.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of a task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation of a task for saving into a file.
     *
     * @return The task string for saving.
     */
    public String toSaveString() {
        return " | " + (isDone ? "1" : "0") + " | " + description;
    }
}
