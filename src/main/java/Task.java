/**
 * Abstract base class for all types of tasks in the SOFI application.
 * Provides common functionality for task management including status tracking.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * 
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon for this task.
     * 
     * @return "[X]" if completed, "[ ]" if not completed
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns a string representation of this task.
     * 
     * @return string representation of the task
     */
    public abstract String toString();
}
