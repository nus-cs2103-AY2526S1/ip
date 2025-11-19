package goober.task;

import java.io.Serializable;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task implements Serializable {
    private String description = "no name";
    private boolean isCompleted = false;
    private Priority priority = Priority.NONE;

    /**
     * Constructs a new Task.
     *
     * @param description the description
     */
    public Task(String description) {
        assert description != null : "Task description must not be null";
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void markComplete() {
        this.isCompleted = true;
    }

    public void unmarkComplete() {
        this.isCompleted = false;
    }

    public Priority getPriority() {
        return (priority == null) ? Priority.NONE : priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        char c = isCompleted ? 'X' : ' ';
        return "[" + c + "](" + getPriority().shortTag() + ") " + description;
    }
}
