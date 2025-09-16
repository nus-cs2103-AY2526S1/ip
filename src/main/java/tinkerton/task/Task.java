package tinkerton.task;

import tinkerton.util.Date;

/**
 * Represents an abstract Task with a name and completion status. Subclasses must implement file
 * storage and date checking methods.
 */
public abstract class Task {
    /** The name of the task. */
    private String name;
    /** True if the task is completed, false otherwise. */
    private boolean isCompleted;

    /**
     * Constructs a Task.
     *
     * @param name The name of the task.
     * @param isCompleted True if the task is completed, false otherwise.
     */
    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks the task as completed.
     */
    public void complete() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void uncomplete() {
        this.isCompleted = false;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return True if completed, false otherwise.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Returns the name of the task.
     *
     * @return The task name.
     */
    public String name() {
        return this.name;
    }

    /**
     * Returns the string representation of the task for display.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String marker = this.isCompleted ? "[X]" : "[ ]";
        return marker + " " + name;
    }

    /**
     * Returns the string representation of the task for file storage.
     *
     * @return String representation for file storage.
     */
    public abstract String toFile();

    /**
     * Checks if the task occurs on the given date.
     *
     * @param date The date to check.
     * @return True if the task occurs on the given date, false otherwise.
     */
    public abstract boolean onDate(Date date);
}
