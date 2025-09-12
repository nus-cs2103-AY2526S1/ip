package tasks;

/**
 * Represents a generic task with a description and completion status.
 * This abstract class provides basic functionality for task management
 * and serves as a base for specific task types.
 */
public abstract class Task {
    // Constants for status icons to eliminate magic strings
    protected static final String DONE_ICON = "[X]";
    protected static final String NOT_DONE_ICON = "[ ]";
    protected static final String SPACE_SEPARATOR = " ";
    
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of this task.
     * 
     * @return The task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the completion status of this task.
     * 
     * @param isDone true if the task is completed, false otherwise
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
    
    /**
     * Checks if this task is completed.
     * 
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the status icon representing the completion state of this task.
     * Returns "[X]" if the task is done, "[ ]" if not done.
     *
     * @return The status icon string.
     */
    public String getStatusIcon() {
        return isDone ? DONE_ICON : NOT_DONE_ICON;
    }

    /**
     * Returns the status string representation of this task.
     * This method must be implemented by subclasses to provide
     * specific status formatting.
     *
     * @return The status string representation.
     */
    public abstract String getStatus();

    /**
     * Converts this task to a text format suitable for storage or transmission.
     * This method must be implemented by subclasses to provide
     * specific text formatting.
     *
     * @return The text representation of this task.
     */
    public abstract String toText();

    /**
     * Returns a string representation of this task including its status,
     * completion icon, and description.
     *
     * @return The complete string representation of this task.
     */
    @Override
    public String toString() {
        return this.getStatus() + this.getStatusIcon() + SPACE_SEPARATOR + this.getDescription();
    }
}