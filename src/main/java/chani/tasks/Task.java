package chani.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents abstract task.
 */
public abstract class Task {
    protected String identifier;
    protected String description;
    protected boolean isDone;

    /**
     * Constructor.
     *
     * @param description The task description; must not be null or empty.
     * @param identifier The type identifier of the task; must not be null.
     */
    public Task(String description, String identifier) {
        assert description != null && !description.isEmpty() : "Task description cannot be null or empty";
        assert identifier != null : "Task identifier cannot be null";

        this.identifier = identifier;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     *
     * @return This task instance, marked done.
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }
    /**
     * Marks the task as undone.
     *
     * @return This task instance, marked undone.
     */
    public Task markAsUndone() {
        this.isDone = false;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns information about the task object in terms of its attributes
     *
     * @return A {@link List} of strings representing this task
     */
    public List<String> toAttributeList() {
        String done = isDone() ? "1" : "0";
        return new ArrayList<>(List.of(identifier, done, description));
    }

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + description;
    }

}
