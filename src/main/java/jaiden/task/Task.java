package jaiden.task;

/**
 * Class for task.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        assert description != null;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for task.
     *
     * @param description Description of task.
     * @param isDone Done status.
     */
    public Task(String description, boolean isDone) {
        assert description != null;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets status icon of task.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Checks if task contains the specified text.
     *
     * @param text Text to check for.
     * @return True if contains text.
     */
    public boolean hasText(String text) {
        assert text != null;
        return this.description.contains(text);
    }

    /**
     * Converts task to string to be saved
     *
     * @return String representation to be saved
     */
    public String save() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Task other)) {
            return false;
        }
        return this.description.equals(other.description) && this.isDone == other.isDone;
    }
}
