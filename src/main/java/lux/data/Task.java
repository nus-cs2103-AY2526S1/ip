package lux.data;

import java.io.Serializable;

/**
 * Abstract base class representing a task in the application.
 *
 * <p>Each task has a textual description and a completion status. Subclasses
 * (for example, todo, deadline, event) provide additional fields and
 * behaviour. Tasks are serializable so they can be persisted by
 * {@link lux.storage.Storage}.
 */
public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Create a new task with the given description. The newly created task
     * is initially not completed.
     *
     * @param description non-null human readable description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a small status icon string used for display.
     *
     * @return "[X]" if the task is done, otherwise "[ ]"
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        isDone = false;
    }

    public boolean contains(String searchTerm) {
        return description.contains(searchTerm);
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
