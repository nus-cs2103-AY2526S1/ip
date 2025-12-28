package tsunderechan.task;

/**
 * Represents a Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Instantiates a Task object.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Instantiates a Task object.
     * This overloaded method allows for manually setting the isDone field.
     *
     * @param description Description of the Task.
     * @param isDone Whether the task has already been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns X if done and a single space if not done.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks task as uncompleted.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the initials of the Task.
     * Either a T for todo task, a D for deadline task, or a E for event task.
     *
     * @return String of a single letter initial.
     */
    public abstract String getIcon();

    /**
     * Returns the timings involved in a Task.
     * Either nothing for todo task, the by section for deadline task, or the from and to section for event task.
     *
     * @return String stating the duration of the task.
     */
    public abstract String getTiming();

    /**
     * Returns the description of a Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether a task is done or not.
     *
     * @return Boolean.
     */
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
