package momo.task;

/**
 * Represents a generic task with a description and completion status.
 * This is an abstract class that is extended by specific types of tasks
 * such as {@link Todo}, {@link Deadline}, and {@link Event}.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new {@code Task} with the given description.
     * The task is initially not done.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new {@code Task} with the given description and completion status.
     *
     * @param description the description of the task.
     * @param isDone whether the task is marked as done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return "X" if the task is done, otherwise a blank space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkFromDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task,
     * including its status icon and description.
     *
     * @return a formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts the task to a string format suitable for saving to disk.
     *
     * @return a string in the format {@code "<status> | <description>"},
     *         where status is 1 if done, 0 if not done.
     */
    public String convertToSaveFormat() {
        return String.format("%d | %s", isDone ? 1 : 0, description);
    }
}
