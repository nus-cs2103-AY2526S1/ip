package lyra.task;

/**
 * Abstract base class representing a task in the Lyra application.
 * Provides basic functionality for task management including status tracking
 * and description storage.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * New tasks are initially marked as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon representing whether the task is done or not.
     *
     * @return "X" if the task is done, " " (space) if not done
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
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
     * Returns a string representation of the task.
     * Format: [status] description
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}


