package jeff.task;

/**
 * Base class for all task types in the Jeff chatbot system.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Constructs a new Task with the specified description and type.
     *
     * @param description the task description
     * @param type the task type (T, D, or E)
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Get visual status icon.
     *
     * @return "[X]" if done, "[ ]" if not done
     */
    public String getStatusIcon() {
        return (isDone ? " [X] " : " [ ] "); // mark done task with X
    }

    /**
     * Mark task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark task as not done.
     */
    public void undo() {
        isDone = false;
    }

    /**
     * Get task description.
     *
     * @return the task description string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Check if task is done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Get task type.
     *
     * @return the task type string
     */
    public String getType() {
        return type;
    }

    /**
     * Return string representation of task.
     *
     * @return the status icon string
     */
    @Override
    public String toString() {
        return getStatusIcon();
    }
}
