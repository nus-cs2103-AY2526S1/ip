package peanutbutter.tasks;

import peanutbutter.TaskType;

/**
 * Represents a generic task with a description, completion status, and type.
 * Provides methods to mark the task as done or not done
 * and to handle the task for display or storage.
 */
public class Task {
    /** The description of the task. */
    protected String description;
    /** Indicates if the task is complete. */
    protected Boolean isDone;
    /** Indicates the type of the task */
    protected TaskType type;

    /**
     * Creates a new Task with the given description and type.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     * @param type The type of the task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return The completeness status of the task.
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task for display,
     * showing its completion status and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Returns a string representation of the task for storage,
     * showing its completion status and description.
     *
     * @return A formatted string representing the task.
     */
    public String makePretty() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

}
