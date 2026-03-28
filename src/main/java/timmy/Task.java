package timmy;

/**
 * Represents a task to be completed by the user.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Creates an incomplete task.
     *
     * @param desc  description of the task.
     */
    Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    /**
     * Marks the task as complete.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the task as a formatted string for Timmy to output.
     *
     * @return task as a formatted string to output on UI.
     */
    abstract String toCompleteString();

    /**
     * Returns the task as a formatted string for Timmy to save.
     *
     * @return task as a formatted string to save in storage.
     */
    abstract String toFileString();

    /**
     * Returns the completion status of the task as a status icon.
     *
     * @return configured status icon for the task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the task as a formatted string with a configured status icon.
     *
     * @return the formatted string with a configured status icon.
     */
    public String toStringWithStatusIcon() {
        return this.getStatusIcon() + " " + this;
    }

    public String toString() {
        return this.description;
    }
}
