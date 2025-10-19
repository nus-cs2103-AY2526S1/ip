package reboot.task;

/**
 * Represents a generic task that can be added to a tasklist.
 * Provides common properties and behaviors for all tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description and isDone.
     * @param description Description of the deadline task.
     * @param isDone Status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the current task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the current task as incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the task as a string that can be stored in a file.
     */
    public String toFileString() {
        return null;
    }

    /**
     * Returns the task as a string that can be displayed.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
