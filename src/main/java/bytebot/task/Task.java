package bytebot.task;

/**
 * Represents a generic task with a description and completion status.
 * This is the base class for specific task types
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description. Tasks not marked as not done by default.
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon telling whether the task is done.
     *
     * @return "X" if the task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
