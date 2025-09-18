package tony.tasks;

import tony.storage.Storage;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String command;
    private boolean isDone = false;

    public Task(String command) {
        this.command = command;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String toString() {
        return (this.isDone() ? "[X]" : "[ ]") + " " + this.command;
    }

    /**
     * Modifies the task to a format to be saved in {@link Storage}.
     * @return The task formatted to a {@link String}.
     */
    public abstract String toDataFormat();
}
