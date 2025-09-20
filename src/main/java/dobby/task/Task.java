package dobby.task;

import java.io.Serializable;

/**
 * Represents a generic task with a description, a completion status,
 * and a task type (e.g., ToDo, Deadline, Event).
 * <p>
 * This class is abstract and is meant to be extended by more specific
 * task types.
 * </p>
 */
public abstract class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String description;
    protected boolean isDone;
    private TaskType type;

    /**
     * Creates a new task with the specified description and type.
     * By default, tasks are marked as not done.
     *
     * @param description The description of the task.
     * @param type The type of this task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false; // default
        this.type = type;
    }

    /**
     * Returns a simple status marker.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the formatted status icon for the task.
     *
     * @return "[X] " if the task is done, "[ ] " otherwise.
     */
    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task including its status
     * and description.
     *
     * @return A string in the format "[X] description" or "[ ] description".
     */
    public String toString() {
        return this.getStatusIcon() + this.getDescription();
    }
}
