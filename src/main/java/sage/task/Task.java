package sage.task;

/**
 * Represents a generic task.
 * This is an abstract class that provides common properties and behaviors for all task types.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     * The task is initially not done.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * 'X' if the task is done, ' ' (space) if not done.
     * @return The status icon string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Get the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is done.
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     * This is an abstract method to be implemented by concrete task types.
     * @return A file-formatted string representing the task.
     */
    public abstract String toFileString();
}
