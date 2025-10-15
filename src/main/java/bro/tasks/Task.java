package bro.tasks;

/**
 * Represents a task that has a description and a status (done or not done).
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the given description. The task is initially not
     * done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new Task with the given description and status.
     *
     * @param description The description of the task.
     * @param isDone      The status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     *
     * @return A message indicating the task has been marked as done.
     */
    public String markAsDone() {
        if (isDone) {
            return "Sorry bro, this task is already marked as done!";
        }
        isDone = true;

        return String.format(
                "Good one bro! I've marked this task as done:\n\t%s",
                toString());
    }

    /**
     * Marks the task as not done.
     *
     * @return A message indicating the task has been marked as not done.
     */
    public String markAsUndone() {
        if (!isDone) {
            return "Sorry bro, this task is already marked as not done!";
        }
        isDone = false;

        return String.format(
                "Aights bro! I've marked this task as not done yet:\n\t%s",
                toString());
    }

    /**
     * Converts the Task to a data string for storage.
     *
     * @return A string representation of the Task for storage.
     */
    public String toDataString() {
        return String.format("|%d|%s", (isDone ? 1 : 0),
                description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                getStatusIcon(),
                description);
    }
}
