package task;

/**
 * The base class for all tasks in this program. It has a generic
 * implementation that includes a description and a completion status,
 * that more specific task types can inherit from (such as {@link ToDo},
 * {@link Event} and {@link Deadline}).
 */
public class Task {
    public String description;
    public boolean isDone;

    /**
     * Constructs a new {@link Task} object with the given description.
     *
     * New tasks are marked as undone.
     *
     * @param description the description/title of the task
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a visual text representation of {@code isDone} as a status icon.
     *
     * @return a status icon for {@code isDone} as a string
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done. Nothing happens if the task is already marked as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone. Nothing happens if the task is already marked as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

