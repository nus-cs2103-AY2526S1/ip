package ming.model;

import java.util.List;

import ming.exception.MingException;


/**
 * Represents a task with a description and a completion status.
 */
public class Task {
    private final String description;
    private final List<String> tags;
    private boolean isDone;

    /**
     * Constructs a Task with the given description. isDone is set to false by default.
     */
    public Task(String description, List<String> tags) {
        this.description = description;
        this.tags = tags;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() throws MingException {
        if (isDone) {
            throw new MingException("ming.model.Task is already done.");
        }
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     *
     * @throws MingException if the task is already not done.
     */
    public void markAsNotDone() throws MingException {
        if (!isDone) {
            throw new MingException("ming.model.Task is not done yet.");
        }
        this.isDone = false;
    }

    /**
     * Returns the description of the task formatted for storage.
     */
    public String toDataString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
