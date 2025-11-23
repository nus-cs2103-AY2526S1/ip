package jinwoo.tasks;

import jinwoo.exception.DukeException;

/**
 * Represents a basic task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Tasks.Task with the given description and marks it as not done.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates and returns a copy for different history states.
     * Refer to copilot suggestion.
     */
    public abstract Task copyTask() throws DukeException;

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public int getIsDone() {
        return (isDone ? 1 : 0);
    }

    public String getDescription() {
        return description;
    }

    public String getJustDescription() {
        return description;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
    //...
}
