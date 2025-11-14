package monarch.tasks;

/**
 * Represents a task in our chatbot.
 */
public abstract class Task implements Comparable<Task> {
    private final String description;
    private boolean isDone;
    private final String type;

    /**
     * Constructor for a Task object.
     * Used only in its subclasses.
     *
     * @param description A description for the task.
     * @param type The type of task, set by subclasses.
     */
    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    /**
     * Returns the current status of the task.
     * "X" for marked, and " " for unmarked.
     *
     * @return An 'X' or a space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Gets the type of task.
     *
     * @return The type as a String.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns additional data depending on the type of Task.
     *
     * @return A list of properties as Strings.
     */
    public abstract String[] getInfo();

    @Override
    public String toString() {
        return (String.format("[%s] %s", getStatusIcon(), description));
    }
}





