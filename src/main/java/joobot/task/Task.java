package joobot.task;

/**
 * Represents an abstract task with a description and completion status.
 * Subclasses of {@code Task} define specific task types (e.g., ToDo, Deadline, Event).
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a new {@code Task} with the given description.
     *
     * @param desc The description of the task.
     */
    public Task(String desc) {
        this.description = desc;
        this.isDone = false; // default false
    }

    /**
     * Marks this task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the status icon for this task.
     *
     * @return "[X]" if the task is done, "[ ]" otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the description of this task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of this task formatted for file storage.
     *
     * @return The string representation of this task in file format.
     */
    public abstract String toFileString();

    /**
     * Returns the type icon of this task.
     * Subclasses must implement this to differentiate between task types.
     *
     * @return The type icon (e.g., "[T]", "[D]", "[E]").
     */
    public abstract String getTypeIcon();

    /**
     * Returns a string representation of this task for display.
     *
     * @return The string showing the task's type, status, and description.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
