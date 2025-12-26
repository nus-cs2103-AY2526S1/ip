package friday.tasks;

/**
 * Represents a possible task that can be isDone by the user. A <code>friday.tasks.Task</code>
 * object corresponds to a task represented by a String and the state of
 * the object, represented by a boolean, with true meaning it has been completed and false meaning it has
 * yet to be completed.
 */
public class Task {
    protected String description;
    protected String tag;
    protected boolean isDone;

    public Task (String description, String tag) {
        this.description = description;
        this.tag = tag;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task,
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a String that corresponds to the representation of
     * the Status of the object.
     *
     * @return the String representation of the status of the object
     */
    public String getStatusIcon() {
        return ( isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns a String representation of the task
     *
     * @return a String representation of the task.
     */
    public String taskAsString() {
        return getStatusIcon() + getDescription();
    }

    /**
     * Check if the task has been marked as isDone.
     * @return a boolean indicating if the task is isDone.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Marks the task as isDone.
     */
    public void markTaskAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markTaskAsUndone() {
        isDone = false;
    }

    /**
     * Returns the tag of the task.
     * @return a string representing the tag of the task.
     */
    public String getTag() {
        return tag;
    }
}
