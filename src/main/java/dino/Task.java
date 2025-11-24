package dino;

/***
 * Represents a general task with a description, completion status and task type.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;
    protected String tag;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.tag = "";
    }

    /**
     * Returns a string to check task status
     *
     * @return string representing task completing status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /** Marks the task as done */
    public void markAsDone() {
        isDone = true;
    }

    /** Marks the task as not done */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the task status: done or undone
     *
     * @return boolean representing task completing status.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the string representation of the task
     *
     * @return string containing the task status and description
     */
    public String toString() {
        String tagString = tag.isEmpty() ? "" : " " + tag;
        return "[" + getStatusIcon() + "] " + description + tagString;
    }

    /**
     * Returns the string representation of the task for storage.
     *
     * @return string of the task in the storing format
     */
    public abstract String toStoreFormat();

    public void setTag(String tag) {
        if (!tag.startsWith("#")) {
            tag = "#" + tag;
        }
        this.tag = tag;
    }
}

