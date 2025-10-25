package dwight.task;

/**
 * Represents a generic task with a description and a completion status. Concrete task types such as
 * {@link ToDo}, {@link Deadline}, and {@link Event} extend this class to provide additional
 * behavior.
 */
public abstract class Task<T extends Task<T>> {

    /** The textual description of the task. */
    private String description;

    /** Whether the task is marked as completed. */
    private boolean isDone;

    /**
     * Creates a new {@code Task} with the specified description. The task is initialized as not
     * completed.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null && !description.trim().isEmpty()
                : "Description for a ToDo task cannot be empty.";
        this.description = description;
        this.isDone = false;
    }

    /** Marks the task as completed. */
    public T mark() {
        this.isDone = true;
        return (T) this;
    }

    /** Marks the task as not completed. */
    public T unmark() {
        this.isDone = false;
        return (T) this;
    }

    public boolean isMarked() {
        return this.isDone;
    }

    /**
     * Checks whether this task's description contains the given keyword or phrase.
     *
     * @param comp The keyword or phrase to check for.
     * @return {@code true} if the description contains the given text, {@code false} otherwise.
     */
    public boolean isMatching(String comp) {
        return this.description.contains(comp);
    }

    public String getUniqueKey() {
        return this.description;
    }

    /**
     * Returns a string representation of the task for display purposes. The representation includes
     * the completion status indicator ({@code [X]} for done, {@code [ ]} for not done) and the
     * description.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }

    /**
     * Returns a serialized representation of the task suitable for saving to storage. The format
     * consists of the completion flag ({@code 1} for done, {@code 0} for not done) followed by the
     * description.
     *
     * @return The serialized string of the task.
     */
    public String serialize() {
        String done = this.isDone ? "1" : "0";
        return done + " | " + this.description;
    }
}
