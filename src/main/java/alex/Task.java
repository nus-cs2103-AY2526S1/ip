package alex;

/**
 * Represents a general task to be included in the task list.
 * A <code>Task</code> contains a description and a status (done or not done).
 */
public abstract class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructs a <code>Task</code> with the specified description.
     * The task is initially not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task suitable for saving to a file.
     *
     * @return File-friendly string representation of the task.
     */
    public abstract String toFileString();

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns an integer representing the task's completion state.
     *
     * @return <code>0</code> if done, <code>1</code> if not done.
     */
    public int doTaskState() {
        if (isDone) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Updates the task's completion status based on an integer value.
     *
     * @param i <code>0</code> to mark as done, <code>1</code> to mark as not done.
     */
    public void handleTask(int i) {
        if (i == 0) {
            this.markTask();
        } else {
            this.unmarkTask();
        }
    }

    /**
     * Returns a string representation of the task suitable for display to the user.
     * Format: <code>[X] description</code> if done, or <code>[ ] description</code> if not done.
     *
     * @return User-friendly string representation of the task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
