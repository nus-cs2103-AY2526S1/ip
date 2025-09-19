package paul.task;

/**
 * A task for Paul.
 * Stores the task's description and completion status.
 */
public abstract class Task {
    public static final String DATE_FORMAT = "MMM dd yyyy";
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
     * @return "X" if the task is completed, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as completed.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
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

    /**
     * Checks if the given object is equal to a Task.
     *
     * @param obj the reference object with which to compare.
     * @return true if this is equal to the Task.
     */
    @Override
    public abstract boolean equals(Object obj);
}
