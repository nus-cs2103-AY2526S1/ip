package jinbot.task;

/**
 * Represents a generic task with a name and a completion status.
 */
public abstract class Task {
    private final String taskName;
    private boolean isDone;
    private Priority priority;

    /**
     * Constructs a Task with the given name.
     * The task is initialized as not done.
     * The priority is initialized as MEDIUM.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        // Set default priority as MEDIUM
        this.priority = Priority.MEDIUM;
    }

    /**
     * Returns priority of the task.
     * @return priority The priority of the task.
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Sets priority of the task.
     * @param priority Priority to be updated to.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }


    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return "X" if the task is done, otherwise a single space {@code " "}.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns whether this task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the name of this task.
     *
     * @return The task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns the string representation of this task in the save file format.
     *
     * @return A formatted string used to save this task to disk.
     */
    public abstract String toSaveFormat();

    /**
     * Returns the string representation of this task for display.
     *
     * @return The string containing the status icon and task name.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.getTaskName();
    }
}
