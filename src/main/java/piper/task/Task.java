package piper.task;

/**
 * Represents a task with a description and completion state.
 * Subclasses provide behavior and formatting specific to task type.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates a task with the given description.
     * New tasks are marked as undone.
     *
     * @param description task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a single letter to identify task type.
     * "T" for Task, "D" for Deadline, "E" for Event.
     *
     * @return task type identifier.
     */
    public String getTaskType() {
        return null;
    }

    /**
     * Returns a status icon used in UI printing.
     *
     * @return "X" if task is done, or a single space otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return task description.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Returns the line used to save task to storage.
     *
     * @return serialized single-line representation of task.
     */
    public String toSerializedLine() {
        String type = this.getTaskType();
        assert type != null && !type.isEmpty() : "Task subclass should have a task type";
        String doneField = this.isDone ? "1" : "0";
        return this.getTaskType() + " | " + doneField + " | " + this.description;
    }

}
