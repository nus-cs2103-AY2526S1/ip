package aries.task;

import java.io.Serializable;

/**
 * Represents a task with a description and completion status.
 */
public abstract class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String DONE_ICON = "X";
    private static final String NOT_DONE_ICON = " ";

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null : "Description cannot be null";
        assert !description.isEmpty() : "Description cannot be empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isDone ? DONE_ICON : NOT_DONE_ICON);
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a unique key for the task.
     *
     * @return The unique key of the task.
     */
    public abstract String getKey();

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    public String norm(String str) {
        return str.replaceAll("\\s+", " ").trim().toLowerCase();
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
