package thopz;

/**
 * Represents a single task with a description and completion status.
 * A task can be marked as done or left unmarked.
 */
public class Task {
    private String tasks;
    private boolean done;

    /**
     * Creates a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param tasks The description of the task (non-null).
     */
    public Task(String tasks) {
        assert tasks != null : "Task description should not be null";
        this.tasks = tasks;
        this.done = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return Task description as a String.
     */
    public String getTasks() {
        return this.tasks;
    }

    /**
     * Marks this task as done.
     */
    public void markTask() {
        this.done = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmarkTask() {
        this.done = false;
    }

    /**
     * Checks whether this task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean getTaskStatus() {
        return this.done;
    }

    /**
     * Returns the storage-friendly format of this task.
     * Subclasses should override this method to define their own save formats.
     *
     * @return String representation of this task for saving.
     */
    public String saveformat() {
        return "NIL";
    }

    /**
     * Returns the string representation of this task,
     * including its completion status and description.
     *
     * @return A formatted string of the task.
     */
    @Override
    public String toString() {
        return (this.done ? "[x] " : "[ ] ") + tasks;
    }
}
