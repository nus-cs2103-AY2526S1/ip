package v.task;

import v.enums.TaskStatus;
import v.enums.TaskType;

/**
 * A simple task with a description and a done/not-done status.
 * Each task is a solemn vow, a promise to be fulfilled or broken.
 */
public abstract class Task {
    private final String description;
    private TaskStatus status;

    /**
     * Creates a new task, unfulfilled, waiting in the wings.
     *
     * @param description The purpose that gives this task meaning.
     */
    public Task(String description) {
        this.description = description;
        this.status = TaskStatus.NOT_DONE;
    }

    /**
     * Marks this task as completed, a promise fulfilled.
     * The deed is done, the vow upheld.
     */
    public void mark() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Returns this task to its unfulfilled state.
     * The wheel turns back, the promise yet to be kept.
     */
    public void unmark() {
        this.status = TaskStatus.NOT_DONE;
    }

    /**
     * Toggles the status of this task between done and not done.
     */
    public void toggleStatus() {
        this.status = status.toggle();
    }

    /**
     * Checks if this task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return status == TaskStatus.DONE;
    }

    /**
     * Reveals the status of this task - a cross for completion,
     * a space for what's yet to be done.
     *
     * @return "X" if done, " " if not.
     */
    public String getStatusIcon() {
        return status.getIcon();
    }

    /**
     * Returns a string representation of the task for saving to file.
     * Subclasses must implement this method.
     *
     * @return A string representation of the task for saving to file.
     * @throws UnsupportedOperationException if the subclass does not implement this method.
     */
    public String toSaveString() {
        throw new UnsupportedOperationException("Subclass should implement");
    }

    /**
     * Sets the done status of the task and returns the task for method chaining.
     *
     * @param done The new done status of the task.
     * @return This task for method chaining.
     */
    public Task setDone(boolean done) {
        this.status = done ? TaskStatus.DONE : TaskStatus.NOT_DONE;
        return this;
    }

    /**
     * Returns the description of this task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of this task.
     *
     * @return The TaskType of this task.
     */
    public abstract TaskType getTaskType();

    /**
     * Returns the type tag for this task (T for Todo, D for Deadline, E for Event).
     *
     * @return A single character string representing the task type.
     */
    public String getTypeTag() {
        return getTaskType().getTag();
    }

    /**
     * Presents this task as a dramatic proclamation.
     *
     * @return A string in the form of [T][X] task description.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getTypeTag(), getStatusIcon(), description);
    }
}
