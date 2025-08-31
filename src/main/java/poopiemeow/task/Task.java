package poopiemeow.task;

/**
 * Abstract base class for all task types in the PoopieMeow application.
 * This class defines the common structure and behavior that all tasks must implement.
 * Tasks represent units of work that users can create, manage, and track.
 *
 *
 * @author tch1001
 * @version 1.0
 */
public abstract class Task {
    /** The description of what the task involves */
    protected String description;
    /** Whether the task has been completed */
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description a description of what the task involves
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     * Sets the completion status to true, indicating the task has been finished.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     * Sets the completion status to false, indicating the task still needs to be done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a visual representation of the task's completion status.
     *
     * @return "X" if the task is done, " " (space) if the task is not done
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Converts the task to its file storage format.
     * This method must be implemented by concrete subclasses to provide
     * their specific serialization format for persistence.
     *
     * @return a string representation of the task suitable for file storage
     */
    public abstract String toFileString();

    /**
     * Returns a string representation of the task.
     * The format includes the completion status icon and the task description.
     *
     * @return a string showing the task's status and description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
