package luna.task;

import java.io.Serializable;

import luna.exception.LunaException;

/**
 * Represents a task that has a name and a done status.
 */
public abstract class Task implements Serializable {
    private final String name;
    private boolean isDone;

    public Task(String name) throws LunaException {
        if (name.isEmpty()) {
            throw new LunaException("The description of a " + getTaskType() + " cannot be empty");
        }
        this.name = name;
    }

    /**
     * Returns the {@code String} representation of the {@code Task}.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }

    /**
     * Returns the {@code String} representation of the {@code Task} type.
     */
    public abstract String getTaskType();

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    public boolean contains(String search) {
        return name.contains(search);
    }
}