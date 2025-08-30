package phuc.model;

/**
 * Abstract base class representing a task in the task management system.
 * Provides common functionality for all task types.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially not completed.
     *
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with specified description and completion status.
     *
     * @param description the task description
     * @param isDone whether the task is completed
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon representing completion state.
     *
     * @return "X" if completed, " " if not completed
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Converts the task to a string format suitable for file storage.
     * Subclasses should override this method.
     *
     * @return a string representation for file storage
     */
    public String writeToFile() {
        return description;
    }

    /**
     * Gets the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return a formatted string showing task status and description
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
