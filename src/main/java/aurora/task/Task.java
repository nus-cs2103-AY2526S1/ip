package aurora.task;

/**
 * Represents a task with a description and completion status.
 * Abstract base class for all subtasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task with the given description.
     *
     * @param description The task details.
     * @param isDone The completion status.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string describing the task and its completion status.
     */
    @Override
    public String toString() {
        return isDone ? "[X] " + description : "[ ] " + description;
    }

    /**
     * Marks this task as complete.
     */
    public abstract void complete();

    /**
     * Returns a text representation of the task.
     *
     * @return A string separated by | representing the task.
     */
    public String toText() {
        return String.format("%s|%s", isDone ? "true" : "false", description);
    }

    /**
     * Returns the description of this task.
     *
     * @return The description as a string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the completion status of this task.
     *
     * @return A boolean representing the task completion status.
     */
    public boolean isDone() {
        return isDone;
    }
}
