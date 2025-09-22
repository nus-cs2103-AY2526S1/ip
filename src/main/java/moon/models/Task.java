package moon.models;

/**
 * Abstract base class representing a task in the Moon chatbot.
 * <p>
 * A task has a name (description) and a done status.
 * Subclasses provide their own storage format and string
 * representation.
 */
public abstract class Task {
    private final String name;
    private boolean done;

    /**
     * Creates a new task with the given name.
     * The task is initially not marked as done.
     *
     * @param name Description of the task
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Returns the name (description) of this task.
     *
     * @return Task description
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns whether this task is marked as done.
     *
     * @return {@code true} if done, {@code false} otherwise
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Sets the done status of this task.
     *
     * @param b {@code true} to mark as done, {@code false} to mark as not done
     */
    public void setDone(boolean b) {
        this.done = b;
    }

    /**
     * Converts this task to its string representation used for persistent storage.
     *
     * @return Storage string in the format specific to the subclass
     */
    public abstract String toStorageString();

    /**
     * Returns the string representation of this task for user display.
     * <p>
     * Format:
     * <ul>
     *   <li>{@code [X] name} if done</li>
     *   <li>{@code [ ] name} if not done</li>
     * </ul>
     *
     * @return Formatted string for display
     */
    @Override
    public String toString() {
        return this.isDone()
                ? "[X] " + this.name
                : "[ ] " + this.name;
    }
}
