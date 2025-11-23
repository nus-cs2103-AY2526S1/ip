package stewie.task;

/**
 * Represents a basic task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Converts the task to file format for storage.
     * Each subclass must implement this method to define its specific serialization format.
     *
     * @return The task in file format.
     */
    public String toFileFormat() {
        return ((this.isDone) ? "1" : "0") + " | " + this.description;
    }

    /**
     * Returns the task description with completion status indicator.
     *
     * @return The formatted task description.
     */
    public String getDescription() {
        return ((this.isDone) ? "[X] " : "[ ] ") + this.description;
    }

    /**
     * Returns if the task is marked.
     *
     * @return boolean of whether the task is done
     */
    public boolean getIsDone() {
        return this.isDone;
    }
}
