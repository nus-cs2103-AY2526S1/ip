package aurora.task;

/**
 * Represents a Aurora.task.task with a description and completion status.
 * Abstract base class for all subtasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task with the given description.
     *
     * @param description The Aurora.task.task details.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the Aurora.task.task.
     *
     * @return A formatted string describing the Aurora.task.task and its completion status.
     */
    @Override
    public String toString() {
        return isDone ? "[X] " + description : "[ ] " + description;
    }

    /**
     * Marks this Aurora.task.task as complete.
     */
    public abstract void complete();

    /**
     * Returns the description of this Aurora.task.task.
     *
     * @return The description as a string.
     */
    public String getDescription() {
        return description;
    }
}
