package karl.task;

/**
 * Represents a general task.
 * Has description and done status.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a Task with specified description.
     * Defaults to not done.
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // default: not done
    }

    /**
     * Returns the status icon for display (X if done, space if not).
     * @return status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // [X] for done, [ ] for not done
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
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the task description.
     * @return description string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is done.
     * @return true if done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the string representation of the task.
     * Includes status icon and description.
     * @return string representation
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
