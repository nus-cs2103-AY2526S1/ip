package taskstuff;

/**
 * Represents a generic task with a description and completion status.
 * This is the abstract base class for all specific task types (e.g., Todo, Deadline, Event).
 */
public abstract class Task {
    /** Description of the task. */
    protected String description;
    /** Completion status of the task. */
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return "[X] " if done, "[ ] " if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Marks the task as done and returns a confirmation message.
     *
     * @return Confirmation message indicating the task is marked as done.
     */
    public String markDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n [X] " + this.description;
    }

    /**
     * Marks the task as not done and returns a confirmation message.
     *
     * @return Confirmation message indicating the task is marked as not done.
     */
    public String unmarkDone() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet:\n [ ] " + this.description;
    }

    /**
     * Returns a string representation of the task suitable for saving to a data file.
     *
     * @return String representation for data file storage.
     */
    public abstract String toDataFile();

    /**
     * Returns the string representation of the task for display.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + this.description;
    }
}
