package task;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the completion status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Generates a command string that can be used to recreate this task.
     * This method is intended to be overridden by subclasses to include specific task types.
     *
     * @return A command string representing the task.
     */
    public String generateCommandString() {
        return this.description;
    }

    /**
     * Generates a history file entry for the task.
     * This method is intended to be overridden by subclasses to include specific task types and statuses.
     *
     * @return A history file entry string representing the task.
     */
    public String generateHistoryFileEntry() {
        return this.description;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a string encoding of the task's completion status for storage purposes.
     *
     * @return "1" if the task is done, "0" otherwise.
     */
    public String getDoneEncoding() {
        return isDone ? "1" : "0";
    }
}
